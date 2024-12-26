package com.socailmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.socailmedia.model.Post;
import com.socailmedia.model.User;
import com.socailmedia.repository.PostRepository;
import com.socailmedia.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostService{
	
	private static final Logger logger= LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Post createPost(Post post,User use) {
		User user= userRepository.findById(use.getId()).get();
		user.getPosts().add(post);
		post.setUser(user);
		return postRepository.save(post);
	}

	@Cacheable(value = "getPostCache", key = "#id")
	public Post getPost(Long id,Long uid) {
		logger.info("Fetching the post..");
		List<Post> list= userRepository.findById(uid).get().getPosts();
		Post pst=new Post();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getId() == id)
				pst=list.get(i);
		}
		return pst;
	}
	
	@Cacheable(value = "getAllPostCache", key = "#id")
	public List<Post> getAllPosts(Long id){
		logger.info("Fetching all the posts..");
		return userRepository.findById(id).get().getPosts();
	}
	
	@CachePut(value = {"getPostCache","getAllPostCache"},key = "#id")
	public Post updatePost(Long id ,Post post,Long uid) {
		
		Post existingpost = getPost(id, uid);
		if(post.getTitle()!=null) {
			existingpost.setTitle(post.getTitle());
		}
		
		if(post.getContent()!=null) {
			existingpost.setContent(post.getContent());
		}
		return postRepository.save(existingpost);
	}
	
	@CacheEvict(value = {"getPostCache","getAllPostCache"},key = "#id")
	public void deletePost(Long id,Long uid) {
		logger.info("Deleting the post..");
		Post post= getPost(id, uid);
		User user= userRepository.findById(uid).get();
		user.getPosts().remove(post);
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i).getId() == id) {
//				list.remove(list.get(i));
//				break;
//			}
//		}
//		user.setPosts(list);
		user.getPosts().forEach(obj->System.out.println(obj));
		userRepository.save(user);
//		postRepository.delete(post);
	}
	
	public Post likePost(Long id,Long uid) {
        Post post = getPost(id, uid);
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }

    public Post dislikePost(Long id, Long uid) {
        Post post = getPost(id, uid);
        post.setDislikes(post.getDislikes() + 1);
        return postRepository.save(post);
    }
}
