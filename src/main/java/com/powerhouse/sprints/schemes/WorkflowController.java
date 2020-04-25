package com.powerhouse.sprints.schemes;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkflowController
{
	@Autowired
	WorkflowSchemeRepository workflowRepo;
	
	@GetMapping("/admin/schemes/")
	public String viewAllWorkflows(Model model) {
		List<WorkflowScheme> allWorkflowSchemes = workflowRepo.findAll();
		model.addAttribute("workflows", allWorkflowSchemes);
		return "schemes/schemeManager";
	}
	
	@GetMapping("/admin/schemes/addWorkflow/")
	public String addWorkflow(Model model) {
		WorkflowScheme w = new WorkflowScheme();
		model.addAttribute("newWorkflow", w);
		return "schemes/workflowSettings";
	}

	@PostMapping("/admin/schemes/workflows/update")
	public String addWorkflow(@ModelAttribute WorkflowScheme w, @RequestParam("numSteps") String numStepsString, 
			@RequestParam("step1") String step1, @RequestParam("step2") String step2, @RequestParam("step3") String step3,
			@RequestParam("step4") String step4, @RequestParam("step5") String step5, @RequestParam("step6") String step6, Model model) {
		List<String> steps = new ArrayList<String>();
		steps.add(step1);
		steps.add(step2);
		steps.add(step3);
		steps.add(step4);
		steps.add(step5);
		steps.add(step6);
		for(int i = 0; i < Integer.parseInt(numStepsString); i++) {
			w.addStep(steps.get(i));
		}
		workflowRepo.save(w);
		return "redirect:/admin/schemes/";
	}
}
