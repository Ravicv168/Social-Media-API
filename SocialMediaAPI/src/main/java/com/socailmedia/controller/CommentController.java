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

import com.socailmedia.model.Comment;
import com.socailmedia.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
		return ResponseEntity.ok(commentService.createComment(comment));
	}
	
	@GetMapping
	public List<Comment> viewAllComments(){
		return commentService.viewAllComments();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Comment> editComment(@PathVariable Long id, @RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.editComment(id, comment));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.ok("Comment deleted Successfully");
	}
	
	@PutMapping("/like/{id}")
	public ResponseEntity<Comment> likeComment(@PathVariable Long id){
		return ResponseEntity.ok(commentService.likeComment(id));
	}
	
	@PutMapping("/dislike/{id}")
	public ResponseEntity<Comment> dislikeComment(@PathVariable Long id){
		return ResponseEntity.ok(commentService.dislikeComment(id));
	}
}
