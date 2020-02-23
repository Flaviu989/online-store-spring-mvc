package com.sda.store.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sda.store.model.Category;
import com.sda.store.service.CategoryService;
import com.sda.store.service.ProductService;
import com.sda.store.service.UserService;

@Controller
public class WelcomeController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String displayLandingPage(Model model, Principal user) {
		List<Category> mainCategory = categoryService.findMainCategories();
		model.addAttribute("mainCategory", mainCategory);
		if (user != null) {
			String username = user.getName();
			model.addAttribute("userName", username);
			model.addAttribute("logo", userService.findUserByName(username).getLogo());
		}
		return "welcome";
	}

}
