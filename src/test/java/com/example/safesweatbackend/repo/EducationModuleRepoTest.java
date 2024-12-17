package com.example.safesweatbackend.repo;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.model.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
class EducationModuleRepoTest {

    @Autowired
    private EducationPostRepo educationPostRepo;

    @Autowired
    private EducationPostBookmarkRepo educationPostBookmarkRepo;

    @Autowired
    private EducationPostLikeRepo educationPostLikeRepo;

    private static UUID educationPostId;

    private static EducationPostBookmarkId educationBookmarkId;

    private static EducationPostLikeId educationLikeId;

    @Test
    @DisplayName("Test 1: Save Education Post")
    @Order(1)
    @Rollback(value = false)
    public void testSaveEducationPost() {
        EducationPost educationPost = new EducationPost();
        educationPost.setCategoryId(UUID.fromString(TestData.CATEGORY_ID));
        educationPost.setTitleEn("Test Title");
        educationPost.setContentEn("Test Content");
        educationPost.setCreatedBy("Test User");
        educationPost.setCreatedDate(new Date());

        EducationPost createdPost = educationPostRepo.save(educationPost);

        educationPostId = createdPost.getPostId();
        assertNotNull(createdPost.getPostId());
    }

    @Test
    @DisplayName("Test 2: Get Education Post By Id")
    @Order(2)
    public void testGetEducationPostById() {
        EducationPost educationPost = educationPostRepo.findById(educationPostId).orElse(null);
        assertNotNull(educationPost);
        assertEquals(educationPostId, educationPost.getPostId());
    }

    @Test
    @DisplayName("Test 3: Update Education Post")
    @Order(3)
    @Rollback(value = false)
    public void testUpdateEducationPost() {
        EducationPost educationPost = educationPostRepo.findById(educationPostId).orElse(null);
        assertNotNull(educationPost);

        String newTitle = "Updated Title";
        educationPost.setTitleEn(newTitle);

        EducationPost updatedPost = educationPostRepo.save(educationPost);
        assertEquals(newTitle, updatedPost.getTitleEn());
    }

    @Test
    @DisplayName("Test 4: Save Education Post Bookmark")
    @Order(4)
    @Rollback(value = false)
    public void testSaveEducationPostBookmark() {
        EducationPostBookmarkId bookmarkId = new EducationPostBookmarkId(UUID.randomUUID(), educationPostId);
        EducationPostBookmark educationPostBookmark = new EducationPostBookmark(bookmarkId, educationPostRepo.findById(educationPostId).get());
        EducationPostBookmark createdPostBookmark = educationPostBookmarkRepo.save(educationPostBookmark);
        educationBookmarkId = createdPostBookmark.getId();
        assertNotNull(createdPostBookmark);
    }

    @Test
    @DisplayName("Test 5: Get Education Post Bookmark By Id")
    @Order(5)
    public void testGetEducationPostBookmarkById() {
        EducationPostBookmark educationPostBookmark = educationPostBookmarkRepo.findById(educationBookmarkId).orElse(null);
        assertNotNull(educationPostBookmark);
        assertEquals(educationBookmarkId, educationPostBookmark.getId());
    }

    @Test
    @DisplayName("Test 6: Delete Education Post Bookmark")
    @Order(6)
    @Rollback(value = false)
    public void testDeleteEducationPostBookmark() {
        educationPostBookmarkRepo.deleteById(educationBookmarkId);
        EducationPostBookmark educationPostBookmark =
                educationPostBookmarkRepo.findById(educationBookmarkId).orElse(null);
        assertNull(educationPostBookmark);
    }

    @Test
    @DisplayName("Test 7: Save Education Post Like")
    @Order(7)
    @Rollback(value = false)
    public void testSaveEducationPostLike() {
        EducationPostLikeId likeId = new EducationPostLikeId(UUID.randomUUID(), educationPostId);
        EducationPostLike educationPostLike = new EducationPostLike(likeId, educationPostRepo.findById(educationPostId).get());
        EducationPostLike createdPostLike = educationPostLikeRepo.save(educationPostLike);
        educationLikeId = createdPostLike.getId();
        assertNotNull(createdPostLike);
    }

    @Test
    @DisplayName("Test 8: Get Education Post Like By Id")
    @Order(8)
    public void testGetEducationPostLikeById() {
        EducationPostLike educationPostLike = educationPostLikeRepo.findById(educationLikeId).orElse(null);
        assertNotNull(educationPostLike);
        assertEquals(educationLikeId, educationPostLike.getId());
    }

    @Test
    @DisplayName("Test 9: Delete Education Post Like")
    @Order(9)
    @Rollback(value = false)
    public void testDeleteEducationPostLike() {
        educationPostLikeRepo.deleteById(educationLikeId);
        EducationPostLike educationPostLike =
                educationPostLikeRepo.findById(educationLikeId).orElse(null);
        assertNull(educationPostLike);
    }

    @Test
    @DisplayName("Test 10: Delete Education Post")
    @Order(10)
    public void testDeleteEducationPost() {
        educationPostRepo.deleteById(educationPostId);
        EducationPost educationPost = educationPostRepo.findById(educationPostId).orElse(null);
        assertNull(educationPost);
    }
}