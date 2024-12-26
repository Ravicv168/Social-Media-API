package com.socailmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.socailmedia.model.Comment;
import com.socailmedia.model.Post;
import com.socailmedia.repository.CommentRepository;
import com.socailmedia.repository.PostRepository;

@Service
public class CommentService {
	
	private static final Logger logger= LoggerFactory.getLogger(CommentService.class);

	@Autowired
	public CommentRepository commentRepository;
	
	@Autowired
	public PostRepository postRepository;
	
	public Comment createComment(Comment comment,Long pid) {
		Post post= postRepository.findById(pid).get();
		post.getComments().add(comment);
		comment.setPost(post);
		return commentRepository.save(comment);
	}
	
	@Cacheable(value = "viewCommentCache", key = "#pid")
	public List<Comment> viewAllComments(Long pid){
		logger.info("Fetching all the comments..");
		return postRepository.findById(pid).get().getComments();	
	}
	
	public Comment editComment(Long id, Comment comment) {
		Comment com = commentRepository.findById(id).get();
		if(comment.getContent()!=null) {
			com.setContent(comment.getContent());
			com.setUpdatedAt(LocalDateTime.now());
		}
		return commentRepository.save(com);
	}
	
	@CacheEvict(value = "viewCommentCache", key = "#pid")
	public void deleteComment(Long cid,Long pid) {
		logger.info("deleting the comment..");
		Comment cmt= commentRepository.findById(cid).get();
		Post post= postRepository.findById(pid).get();
		post.getComments().remove(cmt);
		postRepository.save(post);
	}
	
	public Comment likeComment(Long cid) {
        Comment comment = commentRepository.findById(cid).get();
        comment.setLikes(comment.getLikes() + 1);
        return commentRepository.save(comment);
    }

    public Comment dislikeComment(Long cid) {
    	Comment comment = commentRepository.findById(cid).get();
        comment.setDislikes(comment.getDislikes() + 1);
        return commentRepository.save(comment);
    }
}
