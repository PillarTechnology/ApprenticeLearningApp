package com.pillar.pillarLearningCenter.unitTest.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pillar.pillarLearningCenter.controller.PostController;
//import com.pillar.pillarLearningCenter.service.PostServiceImpl;
import com.pillar.pillarLearningCenter.repository.PostRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostViewTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostRepository postServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testPostRouteLoadsSuccessfullyWhenNoPostsArePresent() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testPostsHtmlView(){
        TestExecutor testExecutor = new TestExecutor();
        testExecutor.execute("classpath:posts/posts.thtest");
        assertTrue(testExecutor.isAllOK());
    }
}





