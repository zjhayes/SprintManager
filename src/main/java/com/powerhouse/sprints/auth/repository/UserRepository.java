package com.powerhouse.sprints.auth.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.project.Project;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByConfirmationToken(String confirmationToken);

	Set<User> findByProjectsIn(Set<Project> projects);

	User findOneByEmail(String email);
}
