package com.socailmedia.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socailmedia.model.User;
import com.socailmedia.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		return ResponseEntity.ok(userService.registerUser(user));
	}
	
	@GetMapping("/name/{username}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String username){
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
	
	@PutMapping("/update/{username}")
	public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
		User existinguser=userService.getUserByUsername(username);
		
		if(user.getEmail()!=null) {
			existinguser.setEmail(user.getEmail());
		}
		
		if(user.getBio()!=null) {
			existinguser.setBio(user.getBio());
		}
		
		if(user.getUsername()!=null) {
			existinguser.setUsername(user.getUsername());
		}
		
		if(user.getPassword()!=null) {
			existinguser.setPassword(user.getPassword());
		}
		return ResponseEntity.ok(userService.updateUser(existinguser));
	}
	
	@PutMapping("{uid}/follow/{fuid}")
	public ResponseEntity<String> follow(@PathVariable Long uid, @PathVariable Long fuid) {
	    userService.followUser(uid, fuid);
	    return ResponseEntity.ok("Followed successfully");
	}
	    
	@PutMapping("{uid}/unfollow/{unfid}")
	public ResponseEntity<String> unfollow(@PathVariable Long uid, @PathVariable Long unfid) {
	    userService.unfollowUser(uid,unfid);
	    return ResponseEntity.ok("Unfollowed successfully");
	}
	
	@GetMapping("{uid}/followers")
	public ResponseEntity<Set<User>> getfollowers(@PathVariable Long uid){
		return ResponseEntity.ok(userService.getFollowers(uid));
	}
	
	@GetMapping("{uid}/following")
	public ResponseEntity<Set<User>> getfollowingUsers(@PathVariable Long uid){
		return ResponseEntity.ok(userService.getFollowingUsers(uid));
	}
}
