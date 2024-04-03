package com.challenges.challengeTwo.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenges.challengeTwo.Entity.User;
import com.challenges.challengeTwo.Repository.Interfaces.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new RuntimeException(new Exception("UserDto Not Found with username: " + username)));

		return UserDetailsImpl.build(user);
	}

}
