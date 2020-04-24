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
import org.springframework.web.bind.annotation.RequestParam;

import com.powerhouse.sprints.project.Project;

@Controller
public class SprintController {

	@Autowired
	SprintRepository sprintRepo;

	@GetMapping({ "/sprints" })
	public String viewAllSprints(Model model) {
		List<Sprint> allSprints = sprintRepo.findAll();
		model.addAttribute("sprints", allSprints);
		return "sprints/sprints";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@GetMapping("projects/{projectID}/sprints/{sprintID}")
	public String viewSprintDetails(@PathVariable("sprintID") long sprintID,@PathVariable("projectID") long projectID, Model model) {
		Sprint s = sprintRepo.getOne(sprintID);
		model.addAttribute("sprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintDetail";
	}

	@GetMapping("/sprints/{sprintID}/addTask")
	public String initTaskForm(@PathVariable("sprintID") long sprintID, Model model) {
		Task t = new Task();
		t.setSprint(sprintRepo.getOne(sprintID));
		model.addAttribute("task", t);
		return "sprints/taskForm";
	}

	@PostMapping("/projects/addSprint")
	public String addSprintToProject(@ModelAttribute Sprint s, Model model) {
		sprintRepo.save(s);
		return "redirect:/projects/" + s.getProject().getId();
	}
	
	@GetMapping("/sprints/edit/{id}")
	public String showUpdateProject(@PathVariable("id") long id, Model model) {
		Sprint s = sprintRepo.findById(id).orElse(null);
		model.addAttribute("newSprint", s);
		return "/sprints/sprintSettings";
	}
	
	@PostMapping("/sprints/update/{id}")
	public String reviseProject(Sprint s, Model model) {
		sprintRepo.save(s);
		return "/projects/" + s.getProject().getId();
	}
}
