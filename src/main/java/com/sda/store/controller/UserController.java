package com.sda.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.store.model.Address;
import com.sda.store.model.User;
import com.sda.store.service.AddressService;
import com.sda.store.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController implements WebMvcConfigurer {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/form")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("address", new Address());
		model.addAttribute("title", "Register user");
		return "user-form";
	}

	@PostMapping("/register")
	public String registerSubmit(@Valid @ModelAttribute("user") User user,
			@ModelAttribute("address") Address address, RedirectAttributes rdAttr, BindingResult bindingResult) {
		String redirectMessage = "Registration successful. Please login " + user.getUsername() + "!";
		if (bindingResult.hasErrors()) {
			return "user-form";
		}
		user.setAdmin(false);
		user.setAddress(address);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getLogo().equals(""))
			user.setLogo("default");

		addressService.saveAddress(address);
		userService.saveUser(user);
		rdAttr.addFlashAttribute("message", redirectMessage);
		return "redirect:/";
	}
}
