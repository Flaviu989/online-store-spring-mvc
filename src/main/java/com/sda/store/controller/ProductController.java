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
		List<Product> productList = productService.findProductByCategoryId(id);
		String categoryName = categoryService.findCategoryById(id);
		model.addAttribute("products", productList);
		model.addAttribute("categoryName", categoryName);
		return "product-list";
	}

	@GetMapping("/product/{id}")
	public String displayProductDetails(Model model, @PathVariable("id") int id) {
		Product product = productService.findProductByIdProduct(id);
		String price = displayPrice(product);
		String decimalPrice = String.valueOf((int) ((product.getItemPrice() - (int) product.getItemPrice()) * 100));
		model.addAttribute("product", product);
		model.addAttribute("price", price);
		model.addAttribute("decimalPrice", decimalPrice);
		return "product";
	}

	private String displayPrice(Product product) {
		int intPrice = (int) product.getItemPrice();
		String price;
		if (intPrice % 1000 == 0)
			return String.valueOf(((double) intPrice / 1000));
		else
			return String.valueOf(intPrice);
	}

}
