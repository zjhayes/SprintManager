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

	@GetMapping("/admin/schemes/addWorkflow/")
	public String addWorkflow( Model model) {
		WorkflowScheme w = new WorkflowScheme();
		model.addAttribute("newWorkflow", w);
		return "schemes/workflowSettings";
	}

	@PostMapping("/admin/schemes/workflows/update")
	public String addWorkflow(@ModelAttribute WorkflowScheme w, Model model) {
		workflowRepo.save(w);
		return "redirect:/dashboard/";
	}
}
