package com.socailmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socailmedia.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
