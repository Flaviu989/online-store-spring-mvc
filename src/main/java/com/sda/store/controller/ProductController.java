package com.sda.store.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.store.model.Category;
import com.sda.store.model.Product;
import com.sda.store.model.Supplier;
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
		String price = displayPrice(product);
		String decimalPrice = String.valueOf((int) ((product.getItemPrice() - (int) product.getItemPrice()) * 100));

		model.addAttribute("product", product);
		model.addAttribute("price", price);
		model.addAttribute("decimalPrice", decimalPrice);
		return "product";
	}

	@PostMapping("/product/{id}")
	public String saveProductWithId(Model model, @PathVariable("id") int id, @ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		String price = displayPrice(product);
		String decimalPrice = String.valueOf((int) ((product.getItemPrice() - (int) product.getItemPrice()) * 100));

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

	@GetMapping("/admin/editProduct")
	public String displayProductEditForm(Model model, @RequestParam("id") int id) {
		String title = "Edit Product Form";
		Product product = productService.findProductByIdProduct(id);
		List<Category> allSubCategories = categoryService.findSubCategory();
		List<Supplier> allSuppliers = supplierService.findAllSuppliers();

		model.addAttribute("product", product);
		model.addAttribute("allSubCategories", allSubCategories);
		model.addAttribute("allSuppliers", allSuppliers);
		model.addAttribute("title", title);
		return "product-form";
	}

	@GetMapping("/admin/addProduct")
	public String displayAddForm(Model model) {
		String title = "Edit Product Form";
		Product product = new Product();
		Date today = java.util.Date.from((LocalDate.now()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		product.setDateAdded(today);
		List<Category> allSubCategories = categoryService.findSubCategory();
		List<Supplier> allSuppliers = supplierService.findAllSuppliers();

		model.addAttribute("product", product);
		model.addAttribute("allSubCategories", allSubCategories);
		model.addAttribute("allSuppliers", allSuppliers);
		model.addAttribute("title", title);
		return "product-form";
	}

	@GetMapping("/search")
	public String searchForEmail(Model model, @RequestParam(defaultValue = " ") String name) {
		List<Product> productList = productService.findAllProductsByNmae(name);

		Product firstProduct = productList.stream().findFirst().orElse(null);
		String price = displayPrice(firstProduct);
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

	@GetMapping("/admin/deleteProduct")
	public String deletePerson(@RequestParam("id") int id, RedirectAttributes rdAttr) {
		String redirect = "redirect:/admin";
		productService.deleteProductWithId(id);
		rdAttr.addFlashAttribute("message", "Product deleted!");
		return redirect;
	}
}
