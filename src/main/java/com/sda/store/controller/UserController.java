package com.sda.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sda.store.model.User;
import com.sda.store.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/registerSubmit")
	public String registerSubmit(@ModelAttribute("user") User user) {
		user.setAdmin(false);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.saveUser(user);
		return "redirect:/";
	}
}
