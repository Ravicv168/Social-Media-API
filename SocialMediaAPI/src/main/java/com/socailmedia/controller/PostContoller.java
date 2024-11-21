package com.socailmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socailmedia.model.Post;
import com.socailmedia.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostContoller {

	@Autowired
	private PostService postService;
	
	@PostMapping("/create")
	public ResponseEntity<Post> createPost(@RequestBody Post post){
		return ResponseEntity.ok(postService.createPost(post));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable Long id){
		return ResponseEntity.ok(postService.getPost(id));
	}
	
	@GetMapping
	public List<Post> getAllPosts() {
	    return postService.getAllPosts();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post){
		return ResponseEntity.ok(postService.updatePost(id, post));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id){
		postService.deletePost(id);
		return ResponseEntity.ok("Post deleted successfully");
	}
	
	@PutMapping("/like/{id}")
    public ResponseEntity<Post> likePost(@PathVariable Long id) {
        Post likedPost = postService.likePost(id);
        return ResponseEntity.ok(likedPost);
    }


    @PutMapping("/dislike/{id}")
    public ResponseEntity<Post> dislikePost(@PathVariable Long id) {
        Post dislikedPost = postService.dislikePost(id);
        return ResponseEntity.ok(dislikedPost);
    }
}
