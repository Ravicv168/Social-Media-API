package com.socailmedia.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.socailmedia.exception.BadRequestException;
import com.socailmedia.exception.EmailAlreadyExistsException;
import com.socailmedia.exception.UserNotFoundException;
import com.socailmedia.model.User;
import com.socailmedia.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private static final Logger logger= LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User registerUser(User user) {
		Optional<User> usr = userRepository.findByEmail(user.getEmail());
		if(usr.isPresent()) {
			throw new EmailAlreadyExistsException(user.getEmail());
		}
		
		if(user.getUsername() == null || user.getUsername().isEmpty()) {
			throw new BadRequestException("Username cannot be empty");
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Cacheable(value = "userProfile", key = "#username")
	public User getUserByUsername(String username) {
		logger.info("Fetching user profile...");
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty())
			throw new UserNotFoundException("username", username);
		
		return user.get();
	}
	
	@Cacheable(value = "userDetails", key = "#email")
	public User getUserByEmail(String email) {
		logger.info("Fetching user profile...");
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty())
			throw new UserNotFoundException("email", email);
		
		return user.get();
	}
	
	public User updateUser(String username, User user) {
		User existinguser=getUserByUsername(username);
		
		if(user.getEmail()!=null) {
			if(getUserByEmail(user.getEmail())!=null)
				throw new EmailAlreadyExistsException(user.getEmail());
			existinguser.setEmail(user.getEmail());
		}
		
		if(user.getBio()!=null) {
			existinguser.setBio(user.getBio());
		}
		
		if(user.getUsername()!=null) {
			existinguser.setUsername(user.getUsername());
		}
		
		if(user.getPassword()!=null) {
			existinguser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		return userRepository.save(existinguser);
	}
	
	@Transactional
	@CacheEvict(value = {"followersCache", "followingCache"}, key = "#uid")
	public void followUser(Long uid, Long fuid) {
		User currentUser = userRepository.findById(uid).get();
		User followUser = userRepository.findById(fuid).get();
	    currentUser.getFollowing().add(followUser);
	    followUser.getFollowers().add(currentUser);
	    userRepository.save(currentUser);
	    userRepository.save(followUser);
	}
	
	@CacheEvict(value = {"followersCache", "followingCache"}, key = "#uid")
	public void unfollowUser(Long uid, Long unfid) {
		User currentUser = userRepository.findById(uid).get();
		User unfollowUser = userRepository.findById(unfid).get();
	    currentUser.getFollowing().remove(unfollowUser);
	    unfollowUser.getFollowers().remove(currentUser);
	    userRepository.save(currentUser);
	    userRepository.save(unfollowUser);
	}
	
	@Cacheable(value = "followersCache", key = "#uid")
	public Set<User> getFollowers(Long uid){
		logger.info("fetching the followers..");
		return userRepository.findById(uid).get().getFollowers();
	}
	
	@Cacheable(value = "followingCache", key = "#uid")
	public Set<User> getFollowingUsers(Long uid){
		logger.info("fetching the following users..");
		return userRepository.findById(uid).get().getFollowing();
	}

	public User login(User user) {
		return getUserByUsername(user.getUsername());
	}
}
