package com.sda.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sda.store.model.Category;
import com.sda.store.service.CategoryService;

@Controller
public class WelcomeController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String displayLandingPage(Model model) {
		List<Category> mainCategory = categoryService.findMainCategories();
		model.addAttribute("mainCategory", mainCategory);
		return "welcome";
	}

}
