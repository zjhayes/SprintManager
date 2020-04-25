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
@RequestMapping("/projects/{projectID}")
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

	@ModelAttribute("project")
	public Project findProject(@PathVariable("projectID") long projectID) {
		return this.projectRepo.findById(projectID).orElse(null);
	}

	@InitBinder("project")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/tasks")
<<<<<<< HEAD
	public String viewProjectTasks(Model model, Project project) {
=======
	public String viewTask(Model model, Project project) {
		Project p = projectRepo.getOne(project.getId());
>>>>>>> 0e2d275... add mapping to view a projects tasks
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
	public String initCreationForm(Project project, Model model) {
		Task task = new Task();
		project.addTask(task);
		model.addAttribute("task", task);
		return VIEWS_TASKS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/tasks/new")
	public String processCreationForm(Project project, Task task, Model model) {
		// TODO check for Sprint ID in URL and assign URL if found
		// (ie /projects/1/tasks/new?sprint=10)
		project.addTask(task);
		this.taskRepo.save(task);
		return "redirect:/projects/{projectID}/tasks";
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
