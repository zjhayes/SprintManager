package com.powerhouse.sprints.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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

	@PreAuthorize("#project.projectMembers.contains(authentication.principal.user) || + hasRole('ROLE_ADMIN')")
	public void save(Project project) {
		projectRepository.save(project);
	}

	@PostAuthorize("returnObject.projectMembers.contains(authentication.principal.user) || + hasRole('ROLE_ADMIN')")
	public Project findById(long projectId) {
		return projectRepository.findById(projectId).orElse(null);
	}

}
