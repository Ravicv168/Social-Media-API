package com.socailmedia.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.socailmedia.exception.BadRequestException;
import com.socailmedia.exception.EmailAlreadyExistsException;
import com.socailmedia.exception.UserNotFoundException;
import com.socailmedia.model.User;
import com.socailmedia.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
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
	
	public User getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty())
			throw new UserNotFoundException("username", username);
		
		return user.get();
	}
	
	public User getUserByEmail(String email) {
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

	public User login(User user) {
		return getUserByUsername(user.getUsername());
	}
}
