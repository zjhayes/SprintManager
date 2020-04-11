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

@Controller
@RequestMapping("/sprints/{sprintID}")
public class TaskController {
	private static final String VIEWS_TASKS_CREATE_OR_UPDATE_FORM = "tasks/createOrUpdateTaskForm";

	private final SprintRepository sprintRepo;
	private final TaskRepository taskRepo;

	public TaskController(SprintRepository sprintRepo, TaskRepository taskRepo) {
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
}
