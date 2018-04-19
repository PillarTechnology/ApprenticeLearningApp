package com.pillar.pillarLearningCenter.controller;

import com.pillar.pillarLearningCenter.model.Post;
import com.pillar.pillarLearningCenter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("postList", allPosts);
        return "posts";
    }

    @GetMapping("/posts/new")
    public String postsNew(Model model) {
        model.addAttribute("post", new Post());
        return "new";
    }

    @PostMapping("/posts/new")
    public String submitPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts";
    }

}