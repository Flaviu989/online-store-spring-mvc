package com.sda.store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sda.store.model.Product;
import com.sda.store.service.CategoryService;
import com.sda.store.service.ProductService;
import com.sda.store.service.SupplierService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/list/{id}/{orderBy}/{pageNumber}")
	public String displayProductsFrom(Model model, @PathVariable("id") int id, @PathVariable("orderBy") String orderBy,
			@PathVariable("pageNumber") int pageNumber) {
		int pageSize = 3;
		Pageable pg = getAppropritatePageable(orderBy, pageNumber, pageSize);
		Page<Product> productList = getAppropriateList(id, pg);
		String categoryName = getCategoryName(id);
		String orderOfList = getOrderOfListTitle(orderBy);

		int nextPage = pageNumber + 1;
		int previousPage = pageNumber - 1;
		int totalPages = productList.getTotalPages();
		boolean hasNextPage = (nextPage < totalPages);
		boolean hasPreviousPage = (previousPage > -1);

		model.addAttribute("products", productList.getContent());
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("listId", id);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("orderOfList", orderOfList);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("previousPage", previousPage);
		model.addAttribute("hasNextPage", hasNextPage);
		model.addAttribute("hasPreviousPage", hasPreviousPage);
		return "product-list";
	}

	private Pageable getAppropritatePageable(String orderBy, int pageNumber, int pageSize) {
		Pageable pg;
		switch (orderBy) {
		case "nameOrder":
			return pg = PageRequest.of(pageNumber, pageSize, Sort.by(Order.asc("name")));
		case "dateOrderAsc":
			return pg = PageRequest.of(pageNumber, pageSize, Sort.by(Order.asc("dateAdded"), Order.asc("name")));
		case "dateOrderDesc":
			return pg = PageRequest.of(pageNumber, pageSize, Sort.by(Order.desc("dateAdded"), Order.asc("name")));
		default:
			return pg = PageRequest.of(pageNumber, pageSize, Sort.by(Order.asc("name")));
		}
	}

	private Page<Product> getAppropriateList(int id, Pageable pg) {
		Page<Product> productList;
		if (id == 0)
			return productList = productService.findAllProducts(pg);
		else
			return productList = productService.findProductByCategoryId(pg, id);
	}

	private String getCategoryName(int id) {
		String categoryName;
		if (id == 0) {
			categoryName = "All products";
		} else
			categoryName = categoryService.findCategoryById(id);
		return categoryName;
	}

	private String getOrderOfListTitle(String orderBy) {
		String orderOfList;
		switch (orderBy) {
		case "nameOrder":
			return "Name";
		case "dateOrderAsc":
			return "Old -> New";
		case "dateOrderDesc":
			return "New -> Old";
		default:
			return "None";
		}
	}

	@GetMapping("/product/{id}")
	public String displayProductDetails(Model model, @PathVariable("id") int id) {
		Product product = productService.findProductByIdProduct(id);
		String priceString = getPriceString(product);
		String decimalPriceString = String.valueOf((int) ((product.getItemPrice() - (int) product.getItemPrice()) * 100));

		model.addAttribute("product", product);
		model.addAttribute("price", priceString);
		model.addAttribute("decimalPrice", decimalPriceString);
		return "product";
	}

	private String getPriceString(Product product) {
		int intPrice = (int) product.getItemPrice();
		String price;
		if (intPrice % 1000 == 0)
			return String.valueOf(((double) intPrice / 1000));
		else
			return String.valueOf(intPrice);
	}

	@GetMapping("/search")
	public String searchForEmail(Model model, @RequestParam(defaultValue = " ") String name) {
		List<Product> productList = productService.findAllProductsByNmae(name);

		Product firstProduct = productList.stream().findFirst().orElse(null);
		String price = getPriceString(firstProduct);
		String decimalPrice = String
				.valueOf((int) ((firstProduct.getItemPrice() - (int) firstProduct.getItemPrice()) * 100));

		List<Product> otherProducts = productList.stream().skip(1).collect(Collectors.toList());
		boolean hasOtherProducts = (otherProducts.size() > 0);

		model.addAttribute("product", firstProduct);
		model.addAttribute("price", price);
		model.addAttribute("decimalPrice", decimalPrice);
		model.addAttribute("products", otherProducts);
		model.addAttribute("searchName", name);
		model.addAttribute("hasOtherProducts", hasOtherProducts);
		return "product-search";
	}

}
