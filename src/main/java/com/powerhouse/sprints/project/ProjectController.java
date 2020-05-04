package com.powerhouse.sprints.project;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.auth.repository.UserRepository;
import com.powerhouse.sprints.schemes.PrioritySchemeRepository;
import com.powerhouse.sprints.schemes.WorkflowSchemeRepository;
import com.powerhouse.sprints.sprint.Sprint;

@Controller
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	WorkflowSchemeRepository workflowRepo;
	@Autowired
	PrioritySchemeRepository priorityRepo;
	@Autowired
	ProjectService projectService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@GetMapping("/projects")
	public String viewAllProjects(Model model) {
		List<Project> allProjects = projectService.findAll();
		model.addAttribute("projects", allProjects);
		return "projects/projects";
	}

	@GetMapping("/addProject")
	public String addProject(Model model) {
		Project p = new Project();
		model.addAttribute("newProject", p);
		model.addAttribute("allUsers", userRepo.findAll());
		model.addAttribute("allWorkflows", workflowRepo.findAll());
		model.addAttribute("allPrioritySchemes", priorityRepo.findAll());
		return "projects/projectSettings";
	}

	@PostMapping("/projects/update")
	public String addProject(@ModelAttribute Project p, @RequestParam("projectMembers") List<Long> users, Model model) {
		p.setCreatedDate(LocalDate.now());
		if (!users.isEmpty()) {
			addMembersToProject(users, p);
		}
		projectService.save(p);
		return "redirect:/projects/" + p.getId();
	}

	@GetMapping("/projects/edit/{id}")
	public String showUpdateProject(@PathVariable("id") long id, Model model) {
		Project p = projectService.findById(id);
		model.addAttribute("newProject", p);
		model.addAttribute("allUsers", userRepo.findAll());
		model.addAttribute("allWorkflows", workflowRepo.findAll());
		model.addAttribute("allPrioritySchemes", priorityRepo.findAll());
		return "projects/projectSettings";
	}

	@PostMapping("/projects/update/{projectID}")
	public String reviseProject(Project p, @RequestParam("projectMembers") List<Long> users, Model model) {
		if (!users.isEmpty()) {
			addMembersToProject(users, p);
		}
		projectService.save(p);
		return "redirect:/projects/{projectID}";
	}

	@GetMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") long id, Model model) {
		Project p = projectRepo.findById(id).orElse(null);
		projectRepo.delete(p);
		return viewAllProjects(model);
	}

	@GetMapping("/projects/{projectID}")
	public String viewProjectDetails(@PathVariable("projectID") long projectID, Model model) {
		Project project = projectService.findById(projectID);
		model.addAttribute("project", project);
		return "projects/projectBoard";
	}

	@GetMapping("/projects/{projectID}/addSprint")
	public String addSprintToProject(@PathVariable("projectID") long projectID, Model model) {
		Sprint s = new Sprint();
		s.setProject(projectService.findById(projectID));
		model.addAttribute("newSprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintSettings";
	}

	private void addMembersToProject(List<Long> users, Project p) {
		p.clearMembers();
		for (Long userID : users) {
			User member = userRepo.findById(userID).orElse(null);
			member.addToProject(p);
			p.addMember(member);
		}
	}
}
