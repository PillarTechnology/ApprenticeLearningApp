package com.pillar.pillarLearningCenter.repository;

import com.pillar.pillarLearningCenter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}