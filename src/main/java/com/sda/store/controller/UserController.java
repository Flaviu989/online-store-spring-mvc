package com.sda.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sda.store.model.User;
import com.sda.store.service.UserService;

@RequestMapping("/register")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/form")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/submit")
	public String registerSubmit(@ModelAttribute("user") User user) {
		user.setAdmin(false);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getLogo().equals(""))
			user.setLogo("default");
		userService.saveUser(user);
		return "redirect:/";
	}
}
