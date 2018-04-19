package com.pillar.pillarLearningCenter.endToEndTests

import geb.junit4.GebTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4)
class CreatePostTest extends GebTest {
    //teardown method here
    /*    @Autowired
    private TestEntityManager entityManager;

    private void teardown() {
        List<Post> posts = entityManager.findAll()
        posts.each { post -> entityManager.remove(post) }
    }*/

    @Test
    void postsNewView_ShouldCreateNewPostAndRedirect_WhenSubmitButtonClicked() {
        go "http://localhost:8080/posts/new"

        $("form").title = "New Title - in memory"
        $("form").content = "Content here"
        $("input", value: "Submit").click()

        assert $("h1")[0].text() == "Posts"
        assert $("div h1")[-1].text() == "New Title - in memory"
        assert $("div p")[-1].text() == "Content here"
        //teardown()
    }
}
