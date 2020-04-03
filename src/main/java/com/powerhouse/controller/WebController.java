package com.powerhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.powerhouse.beans.Sprint;
import com.powerhouse.repository.SprintRepository;

@Controller
public class WebController {

	@Autowired
	SprintRepository sprintRepo;

	@GetMapping("/")
	public String viewSprints(Model model) {
		List<Sprint> allSprints = sprintRepo.findAll();
		model.addAttribute("sprints", allSprints);
		return "sprints";
	}
}
