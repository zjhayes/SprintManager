package com.powerhouse.sprints.auth.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.auth.service.EmailService;
import com.powerhouse.sprints.auth.service.SecurityService;
import com.powerhouse.sprints.auth.service.UserService;

@Controller
public class UserController {
	private UserService userService;
	private SecurityService securityService;

	@Autowired
	public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService,
			EmailService emailService, SecurityService securityService) {
		this.userService = userService;
		this.securityService = securityService;
	}

	@GetMapping("/")
	public String welcome(Model model) {
		return "auth/welcome";
	}

	@GetMapping("/dashboard")
	public ModelAndView showUserDashboard(ModelAndView modelAndView) {
		User authenticatedUser = userService.findByEmail(securityService.findLoggedInUsername());
		modelAndView.setViewName("user/dashboard");
		return modelAndView;
	}
}
