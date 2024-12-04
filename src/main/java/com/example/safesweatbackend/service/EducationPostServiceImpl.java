package com.example.safesweatbackend.service;

import com.example.safesweatbackend.mapper.EducationPostBookmarkMapper;
import com.example.safesweatbackend.mapper.EducationPostCategoryMapper;
import com.example.safesweatbackend.mapper.EducationPostLikeMapper;
import com.example.safesweatbackend.mapper.EducationPostMapper;
import com.example.safesweatbackend.model.dto.*;
import com.example.safesweatbackend.model.entity.EducationPost;
import com.example.safesweatbackend.model.entity.EducationPostBookmark;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import com.example.safesweatbackend.model.entity.EducationPostLike;
import com.example.safesweatbackend.repo.EducationPostLikeRepo;
import com.example.safesweatbackend.repo.EducationPostRepo;
import com.example.safesweatbackend.repo.EducationPostBookmarkRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EducationPostServiceImpl implements EducationPostService {

    private final EducationPostRepo educationPostRepo;

    private final EducationPostLikeRepo educationPostLikeRepo;

    private final EducationPostBookmarkRepo educationPostBookmarkRepo;

    private final EducationPostMapper postMapper;

    private final EducationPostCategoryMapper categoryMapper;

    private final EducationPostLikeMapper likeMapper;

    private final EducationPostBookmarkMapper bookmarkMapper;

    @Override
    public EducationPostDto create(EducationPostDto educationPostDTO) {
        if (educationPostRepo.findCategoryById(educationPostDTO.getCategoryId()) == null) {
            throw new IllegalArgumentException("Invalid Category ID");
        }
        EducationPost educationPost = postMapper.educationPostDtoToEducationPost(educationPostDTO);
        Date currentDate = new Date();
        educationPost.setCreatedDate(currentDate);
        EducationPost educationPostCreated = educationPostRepo.save(educationPost);
        return postMapper.educationPostToDto(educationPostCreated);
    }

    @Override
    public EducationPostDto get(UUID id) {
        EducationPost educationPost = educationPostRepo.findById(id).get();
        return postMapper.educationPostToDto(educationPost);
    }

    @Override
    public List<EducationPostDto> getAll() {
        List<EducationPost> educationPosts = educationPostRepo.findAll();
        return postMapper.educationPostsToDtos(educationPosts);
    }

    @Override
    public EducationPostDto update(EducationPostDto educationPostDTO) {
        if (educationPostRepo.findCategoryById(educationPostDTO.getCategoryId()) == null) {
            throw new IllegalArgumentException("Invalid Category ID");
        }
        UUID postId = educationPostDTO.getPostId();
        EducationPost educationPost = educationPostRepo.findById(postId).get();
        postMapper.updateEducationPostFromDto(educationPostDTO, educationPost);
        educationPost.setLastUpdatedDate(new Date());
        EducationPost educationPostUpdated = educationPostRepo.save(educationPost);
        return postMapper.educationPostToDto(educationPostUpdated);
    }

    @Override
    public void delete(UUID id) {
        educationPostRepo.deleteById(id);
    }

    @Override
    public List<EducationPostCategoryDto> getAllCategories() {
        List<EducationPostCategory> educationPostCategories = educationPostRepo.findAllCategories();
        return categoryMapper.educationPostCategoriesToDtos(educationPostCategories);
    }

    @Override
    public EducationPostLikeDto like(EducationPostLikeDto educationPostLikeDto) {
        EducationPostLike educationPostLike = likeMapper.educationPostLikeDtoToEducationPostLike(educationPostLikeDto);
        EducationPost educationPost = educationPostRepo.findById(educationPostLike.getId().getPostId()).get();
        educationPostLike.setEducationPost(educationPost);
        EducationPostLike educationPostLikeCreated = educationPostLikeRepo.save(educationPostLike);
        return likeMapper.educationPostLikeToDto(educationPostLikeCreated);
    }

    @Override
    public void deleteLike(EducationPostLikeDto educationPostLikeDto) {
        EducationPostLike educationPostLike = likeMapper.educationPostLikeDtoToEducationPostLike(educationPostLikeDto);
        educationPostLikeRepo.delete(educationPostLike);
    }

    @Transactional
    @Override
    public void deleteUserLikes(UUID userId) {
        educationPostLikeRepo.deleteLikesByUserId(userId);
    }

    @Override
    public List<EducationPostSummaryDto> getAllSummaries() {
       return educationPostRepo.findAllSummaries();
    }

    @Override
    public List<EducationPostSummaryDto> getAllBookmarkSummaries(UUID userId) {
        return educationPostRepo.findAllBookmarkSummaries(userId);
    }

    @Override
    public EducationPostBookmarkDto bookmark(EducationPostBookmarkDto educationPostBookmarkDto) {
        EducationPostBookmark educationPostBookmark = bookmarkMapper.educationPostBookmarkDtoToEducationPostBookmark(educationPostBookmarkDto);
        EducationPost educationPost = educationPostRepo.findById(educationPostBookmark.getId().getPostId()).get();
        educationPostBookmark.setEducationPost(educationPost);
        EducationPostBookmark educationPostBookmarkCreated = educationPostBookmarkRepo.save(educationPostBookmark);
        return bookmarkMapper.educationPostBookmarkToDto(educationPostBookmarkCreated);
    }

    @Override
    public void deleteBookmark(EducationPostBookmarkDto educationPostBookmarkDto) {
        EducationPostBookmark educationPostBookmark = bookmarkMapper.educationPostBookmarkDtoToEducationPostBookmark(educationPostBookmarkDto);
        educationPostBookmarkRepo.delete(educationPostBookmark);
    }

    @Transactional
    @Override
    public void deleteUserBookmarks(UUID userId) {
        educationPostBookmarkRepo.deleteUserBookmarks(userId);
    }

    @Override
    public List<EducationPostSummaryDto> getRecommended(UUID userId) {
        EducationPostCategory userFavoriteCategory = educationPostBookmarkRepo.findUserFavouriteCategory(userId);
        if (userFavoriteCategory == null) {
            return new ArrayList<>();
        }
        List<EducationPostSummaryDto> educationPostSummaryDtos =
                educationPostRepo.findPostsByCategoryIdOrderByLikesDesc(userFavoriteCategory.getCategoryId());
        HashSet<UUID> bookmarkedPostIds = new HashSet<>(educationPostBookmarkRepo.findAllBookmarkedPostsByUserId(userId));
        List<EducationPostSummaryDto> result = new ArrayList<>();
        int RECOMMENDED_POSTS_MAX_COUNT = 3;
        for (EducationPostSummaryDto educationPostSummaryDto : educationPostSummaryDtos) {
            if (result.size() == RECOMMENDED_POSTS_MAX_COUNT) {
                break;
            }
            if (!bookmarkedPostIds.contains(educationPostSummaryDto.getPostId())) {
                result.add(educationPostSummaryDto);
            }
        }
        return result;
    }
}
