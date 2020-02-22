package com.sda.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sda.store.service.CategoryService;
import com.sda.store.service.ProductService;
import com.sda.store.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userSrvice;

	@GetMapping("/admin")
	public String displayAdminPanel(Model model) {
		String title = "Admin Panel";
		String tableDdisplay = "none";
		model.addAttribute("title", title);
		model.addAttribute("table_display", tableDdisplay);
		return "admin-panel";
	}

	@GetMapping("/admin/list")
	public String displayAdminPanelLists(Model model, @RequestParam(defaultValue = " ") String type) {
		String title = "Admin Panel Lists";
		List list = getAppropriateList(type);
		String tableDdisplay = getAppropriateString(type);

		model.addAttribute("title", title);
		model.addAttribute("table_display", tableDdisplay);
		model.addAttribute("list", list);
		return "admin-panel";
	}

	private List getAppropriateList(String type) {
		List list;
		switch (type) {
		case "product":
			return productService.findAllProducts();
		case "category":
			return categoryService.findAllCategories();
		case "user":
			return userSrvice.findAllUsers();
		default:
			return null;
		}
	}

	private String getAppropriateString(String type) {
		switch (type) {
		case "product":
			return type;
		case "category":
			return type;
		case "user":
			return type;
		default:
			return "none";
		}
	}

}
