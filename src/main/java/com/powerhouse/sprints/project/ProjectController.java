package com.powerhouse.sprints.project;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.powerhouse.sprints.sprint.Sprint;

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
	
	@GetMapping("/addProject")
	public String addProject(Model model) {
		Project p = new Project();
		model.addAttribute("newProject", p);
		return "projects/project-settings";
	}
	
	@PostMapping("/addProject")
	public String addProject(@ModelAttribute Project p, Model model) {
		p.setCreatedDate(LocalDate.now());
		projectRepo.save(p);
		return viewAllProjects(model);
	}
	
	@GetMapping("/projects/{projectID}")
	public String viewProjectDetails(@PathVariable("projectID") long projectID, Model model) {
		Project p = projectRepo.getOne(projectID);
		model.addAttribute("project", p);
		return "projects/project-details";
	}
	
	@GetMapping("/projects/{projectID}/addSprint")
	public String addSprintToProject(@PathVariable("projectID") long projectID,Model model) {
		
		Sprint s = new Sprint();
		s.setProject(projectRepo.getOne(projectID));
		model.addAttribute("newSprint", s);
		return "sprints/sprintSettings";
	}
	
	@GetMapping("/test")
	public String test(Model model)
	{
		return viewAllProjects(model);
	}
}
