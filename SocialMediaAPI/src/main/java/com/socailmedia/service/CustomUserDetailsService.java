package com.socailmedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.socailmedia.model.CustomUserDetails;
import com.socailmedia.model.User;
import com.socailmedia.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usr = userRepository.findByUsername(username);
		if(usr.isEmpty()) {
			throw new UsernameNotFoundException("User not Found");
		}
		return new CustomUserDetails(usr.get());
	}

}
