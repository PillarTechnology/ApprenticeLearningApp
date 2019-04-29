package com.pillar.pillarLearningCenter.unitTest.service;

import com.pillar.pillarLearningCenter.model.Post;
import com.pillar.pillarLearningCenter.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGetAllPosts(){
        Post post = new Post();
        post.setTitle("Title One");
        post.setContent("Content is Here");
        Post post2 = new Post();
        post2.setTitle("Title 2");
        post2.setContent("Content 2 is Here");
        entityManager.persist(post);
        entityManager.persist(post2);
        entityManager.flush();

        List<Post> postList = postService.getAllPosts();

        assertEquals(postList.get(0), post);
        assertEquals(postList.get(1), post2);
    }

    @Test
    public void testCreatePost(){
        Post post = new Post();
        post.setTitle("Title One");
        post.setContent("Content is Here");
        post.setUsername("max");

        postService.createPost(post);

        Post resultPost = entityManager.find(Post.class, post.getId());

        assertEquals(resultPost, post);
    }

}
