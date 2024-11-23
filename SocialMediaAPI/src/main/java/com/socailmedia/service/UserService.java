package com.socailmedia.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socailmedia.model.User;
import com.socailmedia.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 
	
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}
	
	public Optional<User> getUserByEmail(String username) {
		return userRepository.findByEmail(username);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public void followUser(Long uid, Long fuid) {
		User currentUser = userRepository.findById(uid).get();
		User followUser = userRepository.findById(fuid).get();
	    currentUser.getFollowing().add(followUser);
	    followUser.getFollowers().add(currentUser);
	    userRepository.save(currentUser);
	    userRepository.save(followUser);
	}
	    
	public void unfollowUser(Long uid, Long unfid) {
		User currentUser = userRepository.findById(uid).get();
		User unfollowUser = userRepository.findById(unfid).get();
	    currentUser.getFollowing().remove(unfollowUser);
	    unfollowUser.getFollowers().remove(currentUser);
	    userRepository.save(currentUser);
	    userRepository.save(unfollowUser);
	}
	
	public Set<User> getFollowers(Long uid){
		return userRepository.findById(uid).get().getFollowers();
	}
	
	public Set<User> getFollowingUsers(Long uid){
		return userRepository.findById(uid).get().getFollowing();
	}
}
