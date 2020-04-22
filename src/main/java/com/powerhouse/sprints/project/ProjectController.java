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

import com.powerhouse.sprints.auth.user.User;
import com.powerhouse.sprints.auth.user.UserRepository;
import com.powerhouse.sprints.backlog.Backlog;
import com.powerhouse.sprints.backlog.BacklogRepository;
import com.powerhouse.sprints.sprint.Sprint;
import com.powerhouse.sprints.sprint.Task;

@Controller
public class ProjectController {
	@Autowired
	ProjectRepository projectRepo;
	@Autowired
	UserRepository userRepo;
	
	private final BacklogRepository backlogRepo;
	
	
	public ProjectController(BacklogRepository backlogRepo) {
		this.backlogRepo = backlogRepo;
	}

	@GetMapping("/projects")
	public String viewAllProjects(Model model) {
		List<Project> allSprints = projectRepo.findAll();
		model.addAttribute("projects", allSprints);
		return "projects/projects";
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor( Date.class,
	                         new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
	}

	@GetMapping("/addProject")
	public String addProject(Model model) {
		Project p = new Project();
		model.addAttribute("newProject", p);
		model.addAttribute("allUsers", userRepo.findAll());
		return "projects/projectSettings";
	}

	@PostMapping("/projects/update")
	public String addProject(@ModelAttribute Project p, @RequestParam("projectMembers") List<Long> users, Model model) {
		p.setCreatedDate(LocalDate.now());
		addMembersToProject(users, p);
		projectRepo.save(p);
		return viewAllProjects(model);
	}
	
	@GetMapping("/projects/edit/{id}")
	public String showUpdateProject(@PathVariable("id") long id, Model model)
	{
		Project p = projectRepo.findById(id).orElse(null);
		model.addAttribute("newProject", p);
		model.addAttribute("allUsers", userRepo.findAll());
		return "projects/projectSettings";
	}
	
	@PostMapping("/projects/update/{id}")
	public String reviseProject(Project p, @RequestParam("projectMembers") List<Long> users, Model model)
	{
		addMembersToProject(users, p);
		projectRepo.save(p);	
		return viewAllProjects(model);
	}
	
	@GetMapping("/projects/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model)
	{
		Project p = projectRepo.findById(id).orElse(null);
		projectRepo.delete(p);
		
		return viewAllProjects(model);
	}

	@GetMapping("/projects/{projectID}")
	public String viewProjectDetails(@PathVariable("projectID") long projectID, Model model) {
		Project p = projectRepo.getOne(projectID);
		model.addAttribute("project", p);
		return "projects/projectBoard";
	}

	@GetMapping("/projects/{projectID}/addSprint")
	public String addSprintToProject(@PathVariable("projectID") long projectID, Model model) {

		Sprint s = new Sprint();
		s.setProject(projectRepo.getOne(projectID));
		model.addAttribute("newSprint", s);
		return "sprints/sprintSettings";
	}
	
	@GetMapping("/{projectID}/tasks/new")
	public String initCreationForm(@PathVariable("projectID") long projectID, Backlog backlog, Model model) {
		Task task = new Task();
		backlog.addTask(task);
		model.addAttribute("task", task);
		return "backlogs/createTaskForm";
	}

	@PostMapping("/{projectID}/tasks/new")
	public String processCreationForm(@PathVariable("projectID") long projectID, Backlog backlog, Task task, Model model) {
		backlog.addTask(task);
		//this.backlogRepo.save(task);
		return "redirect:/backlogs/backlogs";
	}
	
	
	
	
	
	
	
	private void addMembersToProject(List<Long> users, Project p) {
		for(Long userID : users) {
			User member = userRepo.findById(userID).orElse(null);
			member.addToProject(p);
			p.addMember(member);
		}
	}
}
