package com.powerhouse.sprints.sprint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.powerhouse.sprints.project.Project;
import com.powerhouse.sprints.project.ProjectRepository;

@Controller
@RequestMapping("/sprints/{sprintID}")
public class TaskController {
	private static final String VIEWS_TASKS_CREATE_OR_UPDATE_FORM = "tasks/createOrUpdateTaskForm";

	private final SprintRepository sprintRepo;
	private final TaskRepository taskRepo;
	private final ProjectRepository projectRepo;

	public TaskController(SprintRepository sprintRepo, TaskRepository taskRepo, ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
		this.sprintRepo = sprintRepo;
		this.taskRepo = taskRepo;
	}

	@ModelAttribute("sprint")
	public Sprint findSprint(@PathVariable("sprintID") long sprintID) {
		return this.sprintRepo.findById(sprintID).orElse(null);
	}

	@InitBinder("sprint")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/tasks/new")
	public String initCreationForm(Sprint sprint, Model model) {
		Task task = new Task();
		sprint.addTask(task);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/new")
	public String processCreationForm(Sprint sprint, Task task, Model model) {
		sprint.addTask(task);
		this.taskRepo.save(task);
		return "redirect:/sprints/{sprintID}";
	}

	@GetMapping("/tasks/{taskId}/edit")
	public String initUpdateOwnerForm(@PathVariable("taskId") long taskId, Model model) {
		Task task = this.taskRepo.findById(taskId).orElse(null);
		model.addAttribute(task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/{taskId}/edit")
	public String processUpdateTaskForm(Task task, Sprint sprint, Model model, @PathVariable("taskId") long taskId) {
			sprint.addTask(task);
			taskRepo.save(task);
			return "redirect:/sprints/{sprintID}";
	}
	
	@PostMapping("/tasks/{taskId}/update")
	public String reviseTask(Task task, Model model) {
		this.taskRepo.save(task);
		model.addAttribute("sprint.tasks", taskRepo.findAll());
		return "redirect:/sprints/{sprintID}";
	}
	
	@GetMapping("/tasks/{taskId}/delete")
	public String deleteTask(@PathVariable("taskId") long taskId, Sprint sprint, Model model) {
		Task task = this.taskRepo.findById(taskId).orElse(null);
		sprint.getTasks().remove(task);
		sprintRepo.save(sprint);
		return "redirect:/sprints/{sprintID}";
	}
}
