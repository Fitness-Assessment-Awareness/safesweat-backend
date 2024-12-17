package com.example.safesweatbackend.controller;

import com.example.safesweatbackend.constant.TestData;
import com.example.safesweatbackend.model.dto.EducationPostDto;
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
    @DisplayName("Test 3: Get Education Post By Id")
    @Order(3)
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
    @Order(4)
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
    @Order(5)
    public void testDeleteEducationPost() throws Exception {
        UUID postId = UUID.randomUUID();
        willDoNothing().given(educationPostService).delete(postId);
        ResultActions response = mockMvc.perform(delete("/education-post/{id}", postId));
        response.andExpect(status().isNoContent())
                .andDo(print());
    }
}