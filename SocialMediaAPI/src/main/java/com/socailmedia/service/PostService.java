package com.socailmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socailmedia.model.Post;
import com.socailmedia.repository.PostRepository;

@Service
public class PostService{

	@Autowired
	private PostRepository postRepository;
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	public Post getPost(Long id) {
		return postRepository.findById(id).get();
	}
	
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	public Post updatePost(Long id ,Post post) {
		Post existingpost = postRepository.findById(id).get();
		if(post.getTitle()!=null) {
			existingpost.setTitle(post.getTitle());
		}
		
		if(post.getContent()!=null) {
			existingpost.setContent(post.getContent());
		}
		return postRepository.save(existingpost);
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	public Post likePost(Long id) {
        Post post = postRepository.findById(id).get();
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }

    public Post dislikePost(Long id) {
        Post post = postRepository.findById(id).get();
        post.setDislikes(post.getDislikes() + 1);
        return postRepository.save(post);
    }
}
