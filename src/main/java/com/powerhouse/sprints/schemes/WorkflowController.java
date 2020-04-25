package com.powerhouse.sprints.schemes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.powerhouse.sprints.project.ProjectRepository;

@Controller
public class WorkflowController
{
	@Autowired
	WorkflowSchemeRepository workflowRepo;
	@Autowired
	ProjectRepository projectRepo;
	
	@GetMapping("/projects/{projectID}/addWorkflow")
	public String addWorkflow( @PathVariable("projectID") Long projectID, Model model) {
		WorkflowScheme w = new WorkflowScheme();
		w.setProject(projectRepo.getOne(projectID));
		model.addAttribute("newWorkflow", w);
		return "schemes/workflowSettings";
	}

	@PostMapping("/projects/{projectID}/workflows/update")
	public String addProject(@ModelAttribute WorkflowScheme w, @PathVariable("projectID") Long projectID, Model model) {
		workflowRepo.save(w);
		return "redirect:/projects/{projectID}";
	}
}
