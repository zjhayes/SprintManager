package com.powerhouse.sprints.auth.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@GetMapping("/")
	public String welcome(Model model) {
		return "auth/welcome";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "auth/login";
	}
}
