package com.powerhouse.sprints.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Collection<Project> findAll() {
		return projectRepository.findAll();
	}
}
