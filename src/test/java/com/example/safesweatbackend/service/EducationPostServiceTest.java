package com.example.safesweatbackend.service;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.mapper.EducationPostMapper;
import com.example.safesweatbackend.model.dto.EducationPostDto;
import com.example.safesweatbackend.model.dto.EducationPostSummaryDto;
import com.example.safesweatbackend.model.entity.EducationPost;
import com.example.safesweatbackend.model.entity.EducationPostCategory;
import com.example.safesweatbackend.repo.EducationPostRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EducationPostServiceTest {

    @Mock
    private EducationPostRepo educationPostRepo;

    @Mock
    private EducationPostMapper postMapper;

    @InjectMocks
    private EducationPostServiceImpl educationPostService;

    private EducationPostDto educationPostDto;

    private EducationPost educationPost;

    @BeforeEach
    public void setup() {
        educationPostDto = new EducationPostDto();
        educationPostDto.setPostId(UUID.randomUUID());
        educationPostDto.setCategoryId(UUID.fromString(TestData.CATEGORY_ID));
        educationPostDto.setTitleEn("Test Title");
        educationPostDto.setContentEn("Test Content");
        educationPostDto.setCreatedBy("Test User");

        educationPost = new EducationPost();
        educationPost.setPostId(educationPostDto.getPostId());
        educationPost.setCategoryId(educationPostDto.getCategoryId());
        educationPost.setTitleEn(educationPostDto.getTitleEn());
        educationPost.setContentEn(educationPostDto.getContentEn());
        educationPost.setCreatedBy(educationPostDto.getCreatedBy());
    }

    @Test
    @DisplayName("Test 1: Save Education Post")
    @Order(1)
    public void testSaveEducationPost() {
        given(educationPostRepo.save(educationPost)).willReturn(educationPost);
        given(educationPostRepo.findCategoryById(any())).willReturn(new EducationPostCategory());
        given(postMapper.educationPostDtoToEducationPost(any())).willReturn(educationPost);
        given(postMapper.educationPostToDto(any())).willReturn(educationPostDto);

        EducationPostDto savedEducationPostDto = educationPostService.create(educationPostDto);

        assertNotNull(savedEducationPostDto);
        assertEquals(educationPostDto.getTitleEn(), savedEducationPostDto.getTitleEn());
        assertEquals(educationPostDto.getContentEn(), savedEducationPostDto.getContentEn());
    }

    @Test
    @DisplayName("Test 2: Get Education Post By Id")
    @Order(2)
    public void testGetEducationPostById() {
        given(educationPostRepo.findById(educationPostDto.getPostId())).willReturn(Optional.of(educationPost));
        given(postMapper.educationPostToDto(educationPost)).willReturn(educationPostDto);

        EducationPostDto foundEducationPostDto = educationPostService.get(educationPostDto.getPostId());

        assertNotNull(foundEducationPostDto);
        assertEquals(educationPostDto.getTitleEn(), foundEducationPostDto.getTitleEn());
    }

    @Test
    @DisplayName("Test 3: Update Education Post")
    @Order(3)
    public void testUpdateEducationPost() {
        String newTitle = "Updated Title";
        educationPostDto.setTitleEn(newTitle);

        given(educationPostRepo.findById(educationPostDto.getPostId())).willReturn(Optional.of(educationPost));
        given(educationPostRepo.save(educationPost)).willReturn(educationPost);
        given(educationPostRepo.findCategoryById(any())).willReturn(new EducationPostCategory());
        given(postMapper.educationPostToDto(educationPost)).willReturn(educationPostDto);

        EducationPostDto updatedEducationPostDto = educationPostService.update(educationPostDto);

        assertNotNull(updatedEducationPostDto);
        assertEquals(newTitle, updatedEducationPostDto.getTitleEn());
    }

    @Test
    @DisplayName("Test 4: Delete Education Post")
    @Order(4)
    public void testDeleteEducationPost() {
        willDoNothing().given(educationPostRepo).deleteById(educationPostDto.getPostId());
        assertDoesNotThrow(() -> educationPostService.delete(educationPostDto.getPostId()));
    }

    @Test
    @DisplayName("Get All Education Post Summaries")
    @Order(5)
    public void getAllEducationPostSummaries() {
        EducationPostSummaryDto summaryDto = new EducationPostSummaryDto(
                UUID.randomUUID(),
                "Summary Title",
                "Summary Content",
                "Test User",
                UUID.randomUUID(),
                0L
        );
        summaryDto.setPostId(UUID.randomUUID());
        summaryDto.setTitleEn("Summary Title");

        given(educationPostRepo.findAllSummaries()).willReturn(List.of(summaryDto));

        List<EducationPostSummaryDto> summaries = educationPostService.getAllSummaries();

        assertNotNull(summaries);
        assertEquals(1, summaries.size());
        assertEquals("Summary Title", summaries.get(0).getTitleEn());
    }

    @Test
    @DisplayName("Get All Bookmark Summaries by User ID")
    @Order(6)
    public void getAllBookmarkSummariesByUserId() {
        UUID userId = UUID.randomUUID();
        EducationPostSummaryDto summaryDto = new EducationPostSummaryDto(
                UUID.randomUUID(),
                "Summary Title",
                "Summary Content",
                "Test User",
                UUID.randomUUID(),
                0L
        );
        summaryDto.setPostId(UUID.randomUUID());
        summaryDto.setTitleEn("Bookmark Summary Title");

        given(educationPostRepo.findAllBookmarkSummaries(userId)).willReturn(List.of(summaryDto));

        List<EducationPostSummaryDto> bookmarkSummaries = educationPostService.getAllBookmarkSummaries(userId);

        assertNotNull(bookmarkSummaries);
        assertEquals(1, bookmarkSummaries.size());
        assertEquals("Bookmark Summary Title", bookmarkSummaries.get(0).getTitleEn());
    }

    @Test
    @DisplayName("Create Education Post with Invalid Category ID")
    @Order(7)
    public void createEducationPostWithInvalidCategoryId() {
        given(educationPostRepo.findCategoryById(educationPostDto.getCategoryId())).willReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            educationPostService.create(educationPostDto);
        });

        assertEquals("Invalid Category ID", exception.getMessage());
    }

    @Test
    @DisplayName("Update Education Post with Invalid Category ID")
    @Order(8)
    public void updateEducationPostWithInvalidCategoryId() {
        given(educationPostRepo.findCategoryById(educationPostDto.getCategoryId())).willReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            educationPostService.update(educationPostDto);
        });

        assertEquals("Invalid Category ID", exception.getMessage());
    }
}