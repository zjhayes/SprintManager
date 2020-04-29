package com.powerhouse.sprints.schemes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchemeController {
	
	@Autowired
	WorkflowSchemeRepository workflowRepo;
	@Autowired
	PrioritySchemeRepository priorityRepo;
	
	@GetMapping("/admin/schemes/")
	public String viewAllSchemes(Model model) {
		List<WorkflowScheme> allWorkflowSchemes = workflowRepo.findAll();
		model.addAttribute("workflows", allWorkflowSchemes);
		System.out.println("TEST TEST" + allWorkflowSchemes.size());
		List<PriorityScheme> allPrioritySchemes = priorityRepo.findAll();
		model.addAttribute("priorities", allPrioritySchemes);
		return "schemes/schemeManager";
	}
}
