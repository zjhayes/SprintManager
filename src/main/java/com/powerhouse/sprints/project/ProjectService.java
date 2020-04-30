package com.powerhouse.sprints.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Project> findAll() {
		return projectRepository.findAll();
	}
}
