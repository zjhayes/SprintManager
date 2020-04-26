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
	public String viewSprintDetails(@PathVariable("sprintID") long sprintID, @PathVariable("projectID") long projectID, Model model) {
		Sprint s = sprintRepo.getOne(sprintID);
		model.addAttribute("sprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintDetail";
	}
	
	@GetMapping("projects/{projectID}/sprints/{sprintID}/board")
	public String viewSprintBoard(@PathVariable("sprintID") long sprintID, @PathVariable("projectID") long projectID, Model model) {
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
		return "sprints/taskForm";
	}

	@PostMapping("/projects/{projectID}/sprints/update")
	public String addSprintToProject(@ModelAttribute Sprint s, Model model) {
		sprintRepo.save(s);
		return "redirect:/projects/" + s.getProject().getId();
	}
	
	@GetMapping("/projects/{projectID}/sprints/{sprintID}/edit")
	public String showUpdateProject(@PathVariable("sprintID") long id, @PathVariable("projectID") long projectID, Model model) {
		Sprint s = sprintRepo.findById(id).orElse(null);
		model.addAttribute("newSprint", s);
		model.addAttribute("projectID", projectID);
		return "sprints/sprintSettings";
	}
	
	@PostMapping("/projects/{projectID}/sprints/update/{sprintID}")
	public String reviseSprint(Sprint s, Model model) {
		sprintRepo.save(s);
		return "redirect:/projects/" + s.getProject().getId();
	}
	
	@GetMapping("/projects/{projectID}/sprints/delete/{sprintID}")
	public String deleteSprint(@PathVariable("sprintID") long sprintID, Model model) {
		Sprint s = sprintRepo.findById(sprintID).orElse(null);
		sprintRepo.delete(s);
		return "redirect:/projects/" + s.getProject().getId();
	}
}
