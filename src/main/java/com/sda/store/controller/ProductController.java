package com.sda.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sda.store.model.Product;
import com.sda.store.service.CategoryService;
import com.sda.store.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list/{id}")
	public String displayProductsFrom(Model model, @PathVariable("id") int id) {
		List<Product> productList = productService.findProductByCategory(id);
		model.addAttribute("products", productList);
		return "product-list";
	}

}
