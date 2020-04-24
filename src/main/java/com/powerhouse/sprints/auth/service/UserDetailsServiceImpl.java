package com.powerhouse.sprints.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.powerhouse.sprints.auth.user.User;
import com.powerhouse.sprints.auth.user.UserDetailsImpl;
import com.powerhouse.sprints.auth.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Not found!");
		}

		return new UserDetailsImpl(user);
	}
}
