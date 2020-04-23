package com.powerhouse.sprints.auth.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("")
	public ModelAndView viewAdminDashboard(ModelAndView modelAndView) {
		modelAndView.setViewName("admin/dashboard");
		return modelAndView;
	}

	@GetMapping("/users")
	public ModelAndView viewUpdateUsers(ModelAndView modelAndView) {
		modelAndView.setViewName("admin/users");
		return modelAndView;
	}

}
