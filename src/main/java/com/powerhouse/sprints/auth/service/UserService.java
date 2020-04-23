package com.powerhouse.sprints.auth.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerhouse.sprints.auth.model.Role;
import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.auth.repository.RoleRepository;
import com.powerhouse.sprints.auth.repository.UserRepository;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public void saveUser(User user) {
		user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findOneByName("ROLE_USER"))));
		userRepository.save(user);
	}
}
