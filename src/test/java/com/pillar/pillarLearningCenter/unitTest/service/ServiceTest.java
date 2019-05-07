package com.pillar.pillarLearningCenter.unitTest.service;

import com.pillar.pillarLearningCenter.model.Post;
import com.pillar.pillarLearningCenter.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceTest {
    @Autowired
    PostRepository postService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGetPostById(){
        Post post = new Post();
        post.setTitle("Title One");
        post.setContent("Content is Here");

        entityManager.persist(post);
        entityManager.flush();

        Post postSaved = postService.getOne(1L);

        assertEquals(post, postSaved);
    }

    @Test
    public void getPostAndThenDeletePostRemovesPostFromList() {
        Post post = new Post();
        post.setTitle("Title One");
        post.setContent("Content is Here");
        Post post2 = new Post();
        post2.setTitle("Title 2");
        post2.setContent("Content 2 is Here");
        entityManager.persist(post);
        entityManager.persist(post2);
        entityManager.flush();
        List<Post> postList = postService.findAll();

        postService.deleteById(postList.get(0).getId());
        List<Post> postListAfterDelete = postService.findAll();

        assertEquals(postList.size() - 1, postListAfterDelete.size());
    }

    @Test
    public void getPostByIdThrowsExceptionWhenPostNotFound() {
        String message = "";
        try {
            Post postSaved = postService.getOne(1L);
            assertEquals(postSaved, 0);
        } catch (EntityNotFoundException e) {
            message = e.getMessage();
        }
        assertEquals("Unable to find com.pillar.pillarLearningCenter.model.Post with id 1", message);
    }


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

        List<Post> postList = postService.findAll();

        assertEquals(postList.get(0), post);
        assertEquals(postList.get(1), post2);
    }

    @Test
    public void testCreatePost(){
        Post post = new Post();
        post.setTitle("Title One");
        post.setContent("Content is Here");
        post.setUsername("max");

        postService.save(post);

        Post resultPost = entityManager.find(Post.class, post.getId());

        assertEquals(resultPost, post);
    }


}
