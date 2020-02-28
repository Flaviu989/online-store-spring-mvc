package com.sda.store.controller;

import java.security.Principal;

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
import org.springframework.web.bind.annotation.RequestParam;
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
		model.addAttribute("title", "Register new user");
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

	@GetMapping("/settings")
	public String displayUserProfile(Model model, @RequestParam("view") String view, Principal user) {
		String username = user.getName();

		model.addAttribute("user", userService.findUserByName(username));
		model.addAttribute("userName", username);
		model.addAttribute("address", addressService.findAddressOfUser(username));
		model.addAttribute("title", username + "'s profile");
		model.addAttribute("view", view);
		return "user-profile";
	}

	@PostMapping("/settings")
	public String saveUserProfile(@RequestParam("view") String view, @Valid @ModelAttribute("user") User user,
			@ModelAttribute("address") Address address, Principal regUser, RedirectAttributes rdAttr) {
		String username = regUser.getName();
		String redirect = "redirect:/user/settings?view=none";
		String redirectMessage = username + "'s profile updated!";

		user.setUsername(userService.findUserByName(username).getUsername());
		user.setPassword(userService.findUserByName(username).getPassword());
		user.setAddress(userService.findUserByName(username).getAddress());
		user.setAdmin(userService.findUserByName(username).isAdmin());
		if (!(user.equals(userService.findUserByName(username))))
			userService.saveUser(user);
		else
			redirectMessage = "No changes were made!";

		address.setUser(userService.findUserByName(username));
		address.setIdAddress(addressService.findAddressOfUser(username).getIdAddress());
		if (!(address.equals(addressService.findAddressOfUser(username))))
			addressService.saveAddress(address);
		else
			redirectMessage = "No changes were made!";

		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}
}
