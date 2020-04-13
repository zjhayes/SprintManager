package com.powerhouse.sprints.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController
{
	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping("/projects")
	public String viewAllProjects(Model model) {
		List<Project> allSprints = projectRepo.findAll();
		model.addAttribute("projects", allSprints);
		return "projects/projects";
	}
	
	
	
}
