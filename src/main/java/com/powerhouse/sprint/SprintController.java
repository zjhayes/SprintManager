package com.powerhouse.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SprintController {

	@Autowired
	SprintRepository sprintRepo;

	@GetMapping({"/sprints", "/"})
	public String viewAllSprints(Model model) {
		List<Sprint> allSprints = sprintRepo.findAll();
		model.addAttribute("sprints", allSprints);
		return "sprints/sprints";
	}

	@GetMapping("/sprints/{sprintID}")
	public String viewSprintDetails(@PathVariable("sprintID") long sprintID, Model model) {
		Sprint s = sprintRepo.getOne(sprintID);
		model.addAttribute("sprint", s);
		return "sprints/sprintDetail";
	}
	
	@GetMapping("/sprints/{sprintID}/addTask")
	public String initTaskForm(@PathVariable("sprintID") long sprintID, Model model) {
		Task t = new Task();
		t.setSprint(sprintRepo.getOne(sprintID));
		model.addAttribute("task", t);
		return "sprints/taskForm";
	}	

}
