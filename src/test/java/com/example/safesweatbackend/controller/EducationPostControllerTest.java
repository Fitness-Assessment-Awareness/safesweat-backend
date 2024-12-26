package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.model.dto.*;
import com.example.safesweatbackend.service.EducationPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EducationPostController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EducationPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EducationPostService educationPostService;

    private EducationPostDto educationPostDto;

    private EducationPostSummaryDto educationPostSummaryDto;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        educationPostDto = new EducationPostDto();
        educationPostDto.setPostId(UUID.randomUUID());
        educationPostDto.setCategoryId(UUID.fromString(TestData.CATEGORY_ID));
        educationPostDto.setTitleEn("Test Title");
        educationPostDto.setContentEn("Test Content");
        educationPostDto.setCreatedBy("Test User");
        educationPostSummaryDto = new EducationPostSummaryDto(
                UUID.randomUUID(), "Test Title", "Test User", "Image",
                UUID.randomUUID(), 0L);
    }

    @Test
    @DisplayName("Test 1: Save Education Post")
    @Order(1)
    public void testSaveEducationPost() throws Exception {
        given(educationPostService.create(any(EducationPostDto.class))).willReturn(educationPostDto);
        ResultActions response = mockMvc.perform(post("/education-post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(educationPostDto))
        );
        response.andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.titleEn").value(educationPostDto.getTitleEn()))
                .andExpect(jsonPath("$.contentEn").value(educationPostDto.getContentEn()));
    }

    @Test
    @DisplayName("Test 2: Get Education Posts")
    @Order(2)
    public void testGetEducationPosts() throws Exception {
        given(educationPostService.getAll()).willReturn(List.of(educationPostDto));
        ResultActions response = mockMvc.perform(get("/education-post/list"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Test 2: Get Education Posts Summary")
    @Order(3)
    public void testGetEducationPostsSummary() throws Exception {
        given(educationPostService.getAllSummaries()).willReturn(List.of(educationPostSummaryDto));
        ResultActions response = mockMvc.perform(get("/education-post/list-summary"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Test 3: Get Education Post By Id")
    @Order(4)
    public void testGetEducationPostById() throws Exception {
        given(educationPostService.get(educationPostDto.getPostId())).willReturn(educationPostDto);
        ResultActions response = mockMvc.perform(get("/education-post/{id}", educationPostDto.getPostId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.titleEn").value(educationPostDto.getTitleEn()))
                .andExpect(jsonPath("$.contentEn").value(educationPostDto.getContentEn()));
    }

    @Test
    @DisplayName("Test 4: Update Education Post")
    @Order(5)
    public void testUpdateEducationPost() throws Exception {
        String newTitle = "Updated Title";
        EducationPostDto updatedEducationPostDto = new EducationPostDto();
        updatedEducationPostDto.setPostId(educationPostDto.getPostId());
        updatedEducationPostDto.setCategoryId(educationPostDto.getCategoryId());
        updatedEducationPostDto.setTitleEn(newTitle);
        given(educationPostService.update(updatedEducationPostDto)).willReturn(updatedEducationPostDto);

        ResultActions response = mockMvc.perform(patch("/education-post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEducationPostDto))
        );

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.titleEn").value(newTitle));
    }

    @Test
    @DisplayName("Test 5: Delete Education Post")
    @Order(6)
    public void testDeleteEducationPost() throws Exception {
        UUID postId = UUID.randomUUID();
        willDoNothing().given(educationPostService).delete(postId);
        ResultActions response = mockMvc.perform(delete("/education-post/{id}", postId));
        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Get Bookmark Post Summaries by User ID")
    @Order(7)
    public void getBookmarkPostSummariesByUserId() throws Exception {
        UUID userId = UUID.randomUUID();
        given(educationPostService.getAllBookmarkSummaries(userId)).willReturn(List.of(educationPostSummaryDto));
        ResultActions response = mockMvc.perform(get("/education-post/list-summary/bookmark/{userId}", userId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Get Recommended Education Posts by User ID")
    @Order(8)
    public void getRecommendedEducationPostsByUserId() throws Exception {
        UUID userId = UUID.randomUUID();
        given(educationPostService.getRecommended(userId)).willReturn(List.of(educationPostSummaryDto));
        ResultActions response = mockMvc.perform(get("/education-post/list-summary/recommended/{userId}", userId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Get Education Post Categories")
    @Order(9)
    public void getEducationPostCategories() throws Exception {
        EducationPostCategoryDto categoryDto = new EducationPostCategoryDto();
        given(educationPostService.getAllCategories()).willReturn(List.of(categoryDto));
        ResultActions response = mockMvc.perform(get("/education-post/categories"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    @DisplayName("Like Education Post")
    @Order(10)
    public void likeEducationPost() throws Exception {
        EducationPostLikeDto likeDto = new EducationPostLikeDto();
        likeDto.setPostId(UUID.randomUUID());
        given(educationPostService.like(any(EducationPostLikeDto.class))).willReturn(likeDto);
        ResultActions response = mockMvc.perform(post("/education-post/like")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeDto))
        );

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.postId").value(likeDto.getPostId().toString()));
    }

    @Test
    @DisplayName("Remove Like from Education Post")
    @Order(11)
    public void removeLikeFromEducationPost() throws Exception {
        EducationPostLikeDto likeDto = new EducationPostLikeDto();
        willDoNothing().given(educationPostService).deleteLike(likeDto);
        ResultActions response = mockMvc.perform(post("/education-post/dislike")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeDto))
        );

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Remove All Likes by User ID")
    @Order(12)
    public void removeAllLikesByUserId() throws Exception {
        UUID userId = UUID.randomUUID();
        willDoNothing().given(educationPostService).deleteUserLikes(userId);
        ResultActions response = mockMvc.perform(delete("/education-post/likes/{userId}", userId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Bookmark Education Post")
    @Order(13)
    public void bookmarkEducationPost() throws Exception {
        EducationPostBookmarkDto bookmarkDto = new EducationPostBookmarkDto();
        bookmarkDto.setPostId(UUID.randomUUID());
        given(educationPostService.bookmark(any(EducationPostBookmarkDto.class))).willReturn(bookmarkDto);
        ResultActions response = mockMvc.perform(post("/education-post/bookmark")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookmarkDto))
        );

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.postId").value(bookmarkDto.getPostId().toString()));
    }

    @Test
    @DisplayName("Remove Bookmark from Education Post")
    @Order(14)
    public void removeBookmarkFromEducationPost() throws Exception {
        EducationPostBookmarkDto bookmarkDto = new EducationPostBookmarkDto();
        bookmarkDto.setPostId(UUID.randomUUID());
        willDoNothing().given(educationPostService).deleteBookmark(bookmarkDto);
        ResultActions response = mockMvc.perform(post("/education-post/bookmark-remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookmarkDto))
        );

        response.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Remove All Bookmarks by User ID")
    @Order(15)
    public void removeAllBookmarksByUserId() throws Exception {
        UUID userId = UUID.randomUUID();
        willDoNothing().given(educationPostService).deleteUserBookmarks(userId);
        ResultActions response = mockMvc.perform(delete("/education-post/bookmark/{userId}", userId));

        response.andExpect(status().isNoContent())
                .andDo(print());
    }
}