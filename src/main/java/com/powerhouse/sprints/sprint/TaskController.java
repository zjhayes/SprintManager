package com.powerhouse.sprints.sprint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.powerhouse.sprints.project.Project;
import com.powerhouse.sprints.project.ProjectRepository;

@Controller
@RequestMapping("/projects/{projectID}")
public class TaskController {
	private static final String VIEWS_TASKS_CREATE_OR_UPDATE_FORM = "tasks/createOrUpdateTaskForm";

	private final SprintRepository sprintRepo;
	private final TaskRepository taskRepo;
	private final ProjectRepository projectRepo;

	@Autowired
	public TaskController(SprintRepository sprintRepo, TaskRepository taskRepo, ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
		this.sprintRepo = sprintRepo;
		this.taskRepo = taskRepo;
	}

	@ModelAttribute("project")
	public Project findProject(@PathVariable("projectID") long projectID) {
		return this.projectRepo.findById(projectID).orElse(null);
	}

	@InitBinder("project")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/tasks")
	public String viewProjectTasks(Model model, Project project) {
		model.addAttribute("project", project);
		return "projects/backlogs";
	}

	@GetMapping("/tasks/{taskID}")
	public String viewTask(@PathVariable("taskID") long taskID, Model model, Project project) {
		Task task = taskRepo.getOne(taskID);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/{taskID}")
	public String upDateTask(Task task, Model model, Project project) {
		project.addTask(task);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@GetMapping("/tasks/new")
	public String initCreationForm(Project project, Model model, @RequestParam(required = false) Long sprintid) {
		Task task = new Task();
//		if (sprintid != null) {
//			Sprint sprint = sprintRepo.getOne(sprintid);
//			sprint.addTask(task);
//		}
		project.addTask(task);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/new")
	public String processCreationForm(Project project, @RequestParam(required = false) Long sprintid, Task task,
			Model model) {
		if (sprintid != null) {
			Sprint sprint = sprintRepo.getOne(sprintid);
			sprint.addTask(task);
		}
		project.addTask(task);
		this.taskRepo.save(task);
		return "redirect:/projects/{projectID}/tasks";
	}

/*
	
	@GetMapping("{sprintID}/board/tasks/new")
	public String initCreationForm2(@PathVariable("sprintID") long sprintID, Project project, Model model) {
		Task task = new Task();
		task.getSprint().setId(sprintID);
		project.addTask(task);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}
		
	@PostMapping("/{sprintID}/board/tasks/new")
	public String processCreationForm2(@PathVariable("sprintID") long sprintID, Sprint sprint, Task task, Model model) {
		sprint.addTask(task);
		this.taskRepo.save(task);
		return "redirect:/projects/{projectID}/sprints/{sprintID}/board";
	}
	
*/	

	@GetMapping("/tasks/{taskId}/edit")
	public String initUpdateOwnerForm(@PathVariable("taskId") long taskId, Project project, Model model) {
		Task task = this.taskRepo.findById(taskId).orElse(null);
		model.addAttribute(task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/{taskId}/edit")
	public String processUpdateTaskForm(@PathVariable("projectID") long projectID, Task task, Project project,
			Model model) {
		project.addTask(task);
		taskRepo.save(task);
		return "redirect:/projects/{projectID}/tasks";
	}

	@PostMapping("/tasks/{taskId}/update")
	public String reviseTask(Task task, Model model) {
		this.taskRepo.save(task);
		model.addAttribute("sprint.tasks", taskRepo.findAll());
		return "redirect:/sprints/{sprintID}";
	}

	@GetMapping("/tasks/{taskId}/moveToSprint")
	public String moveTaskToSprint(@PathVariable("projectID") long projectID, @PathVariable("taskId") long taskId, Project project, Model model) {
		Task task = this.taskRepo.findById(taskId).orElse(null);
		System.out.println("*******************************");
		System.out.println("Task ID = " + task.getId());
		try {
			System.out.println("Task sprint.id = " + task.getSprint().getId());
		} catch (Exception e) {
			System.out.println("Task sprint.id is null");
		}
		
		int taskIndex = project.getBacklog().indexOf(task);
		
		
		long sprintID = 36; // predefined test sprint
		Sprint sprint = this.sprintRepo.findById(sprintID).orElse(null);
		task.setSprint(sprint);
		System.out.println("*******************************");
		System.out.println("Task ID = " + task.getId());
		try {
			System.out.println("Task sprint.id = " + task.getSprint().getId());
		} catch (Exception e) {
			System.out.println("Task sprint.id is null");
		}
		project.getBacklog().get(taskIndex).setSprint(sprint);
		//project.getBacklog().remove(task);
		projectRepo.save(project);
		sprint.addTask(task);
		sprintRepo.save(sprint);
		
		return "redirect:/projects/{projectID}/tasks";
	}
	
	@GetMapping("/tasks/{taskId}/delete")
	public String deleteTask(@PathVariable("projectID") long projectID, @PathVariable("taskId") long taskId,
			Project project, Model model) {
		Task task = this.taskRepo.findById(taskId).orElse(null);
		project.getBacklog().remove(task);
		projectRepo.save(project);
		return "redirect:/projects/{projectID}/tasks";
	}
}
