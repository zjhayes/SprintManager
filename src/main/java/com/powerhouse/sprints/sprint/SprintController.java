package com.powerhouse.sprints.sprint;

import java.util.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.powerhouse.sprints.project.Project;
import com.powerhouse.sprints.project.ProjectRepository;

@Controller
@RequestMapping("/projects/{projectID}")
public class SprintController {
	private final SprintRepository sprintRepo;
	private final TaskRepository taskRepo;
	private final ProjectRepository projectRepo;

	@Autowired
	public SprintController(SprintRepository sprintRepo, TaskRepository taskRepo, ProjectRepository projectRepo) {
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

	// IS this used?
//	@GetMapping("/sprints")
//	public String viewAllSprints(Model model, Project project) {
//		List<Sprint> allSprints = sprintRepo.findAllByProjectId(project.getId());
//		model.addAttribute("sprints", allSprints);
//		return "sprints/sprints";
//	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@GetMapping("/sprints/{sprintID}")
	public String viewSprintDetails(@PathVariable("sprintID") long sprintID, Project project, Model model) {
		Sprint s = sprintRepo.getOne(sprintID);
		model.addAttribute("sprint", s);
		model.addAttribute("project", project);
		return "sprints/sprintDetail";
	}

	@GetMapping("/sprints/{sprintID}/board")
	public String viewSprintBoard(@PathVariable("sprintID") long sprintID, @PathVariable("projectID") long projectID,
			Model model) {
		Sprint s = sprintRepo.getOne(sprintID);
		model.addAttribute("sprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintBoard";
	}

	@GetMapping("/sprints/{sprintID}/addTask")
	public String initTaskForm(@PathVariable("sprintID") long sprintID, Model model) {
		Task t = new Task();
		t.setSprint(sprintRepo.getOne(sprintID));
		model.addAttribute("task", t);
		return "tasks/createOrUpdateTaskForm";
	}

	@PostMapping("/sprints/update")
	public String addSprintToProject(@ModelAttribute Sprint s, Model model) {
		sprintRepo.save(s);
		return "redirect:/projects/" + s.getProject().getId();
	}

	@GetMapping("/sprints/{sprintID}/edit")
	public String showUpdateSprint(@PathVariable("sprintID") long id, @PathVariable("projectID") long projectID,
			Model model) {
		Sprint s = sprintRepo.findById(id).orElse(null);
		model.addAttribute("newSprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintSettings";
	}

	@PostMapping("/sprints/update/{sprintID}")
	public String reviseSprint(Sprint s, Model model) {
		sprintRepo.save(s);
		return "redirect:/projects/" + s.getProject().getId();
	}

	@GetMapping("/sprints/delete/{sprintID}")
	public String deleteSprint(@PathVariable("sprintID") long sprintID, Model model) {
		Sprint s = sprintRepo.findById(sprintID).orElse(null);
		sprintRepo.delete(s);
		return "redirect:/projects/" + s.getProject().getId();
	}
}
