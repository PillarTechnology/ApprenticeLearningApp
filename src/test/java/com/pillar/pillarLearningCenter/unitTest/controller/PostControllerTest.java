package com.pillar.pillarLearningCenter.unitTest.controller;

import com.pillar.pillarLearningCenter.controller.PostController;
import com.pillar.pillarLearningCenter.model.Post;
import com.pillar.pillarLearningCenter.repository.PostRepository;
//import com.pillar.pillarLearningCenter.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostControllerTest {

    @InjectMocks
    private PostController postController;

    @Mock
    private PostRepository postService;


    private Model model = new ExtendedModelMap();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void posts_ShouldPopulateModelWithPostData_WhenServiceReturnsPostData() {
        Post onePost = new Post();
        onePost.setTitle("Title One");
        onePost.setUsername("Max");
        Post twoPost = new Post();
        twoPost.setTitle("Title Two");
        twoPost.setUsername("Max");
        List<Post> expected = new ArrayList<>();
        expected.add(onePost);
        expected.add(twoPost);
        Mockito.when(postService.findAll()).thenReturn(expected);

        postController.posts(model);

        List<Post> actual = (List<Post>) model.asMap().get("postList");

        assertEquals(expected, actual);
    }

    @Test
    public void posts_shouldReturnPostsRouteString_whenCalled(){
        assertEquals("posts", postController.posts(model));
    }

    @Test
    public void postsNew_ShouldPopulateModelWithEmptyPost_WhenCalled() {
        Post post = new Post();

        postController.postsNew(model);

        Post actual = (Post) model.asMap().get("post");

        assertEquals(post.getId(), actual.getId());
        assertEquals(post.getTitle(), actual.getTitle());
        assertEquals(post.getContent(), actual.getContent());
    }

    @Test
    public void postsNew_shouldReturnNewRouteString_WhenCalled(){
        assertEquals("new", postController.postsNew(model));
    }

    @Test
    public void submitPost_ShouldReturnPostsRedirectString_WhenCalled(){
        Post post = new Post();
        assertEquals("redirect:/posts", postController.submitPost(post));
    }

    @Test
    public void submitPost_ShouldUseServiceToCreatePost_WhenCalled(){
        Post post = new Post();
        post.setTitle("Dummy Title");
        post.setContent("Dummy Content");
        postController.submitPost(post);
        Mockito.verify(postService).save(post);
    }

}