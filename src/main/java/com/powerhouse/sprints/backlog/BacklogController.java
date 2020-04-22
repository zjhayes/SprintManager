package com.powerhouse.sprints.backlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BacklogController {
	
	@Autowired
	BacklogRepository backlogRepo;
	
	@GetMapping("/backlogs")
	public String viewAllBacklogs(Model model) {
		List<Backlog> backlogTasks = backlogRepo.findAll();
		model.addAttribute("backlogs", backlogTasks);
		return "backlogs/backlogs";
	}
}
