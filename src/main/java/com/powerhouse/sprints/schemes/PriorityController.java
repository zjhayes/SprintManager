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
public class PriorityController {
	
	@Autowired
	PrioritySchemeRepository priorityRepo;
	
	@GetMapping("/admin/schemes/addPriority/")
	public String addWorkflow(Model model) {
		PriorityScheme p = new PriorityScheme();
		model.addAttribute("newPriorityScheme", p);
		return "schemes/prioritySchemeSettings";
	}

	@PostMapping("/admin/schemes/priorities/update")
	public String addWorkflow(@ModelAttribute PriorityScheme p, @RequestParam("numSteps") String numStepsString, 
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
			p.addPriority(steps.get(i));
		}
		priorityRepo.save(p);
		return "redirect:/admin/schemes/";
	}
}
