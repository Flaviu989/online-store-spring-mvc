package com.sda.store.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.store.model.Category;
import com.sda.store.model.Product;
import com.sda.store.model.Supplier;
import com.sda.store.service.AddressService;
import com.sda.store.service.CategoryService;
import com.sda.store.service.OrderItemService;
import com.sda.store.service.OrderService;
import com.sda.store.service.ProductService;
import com.sda.store.service.SupplierService;
import com.sda.store.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private SupplierService supplierService;

	@GetMapping("")
	public String displayAdminPanel(Model model) {
		String title = "Admin Panel";
		String tableDdisplay = "none";
		model.addAttribute("title", title);
		model.addAttribute("table_display", tableDdisplay);
		return "admin-panel";
	}

	@GetMapping("/list")
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
			return userService.findAllUsers();
		case "order":
			return orderService.findAllOrders();
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
		case "order":
			return type;
		default:
			return "none";
		}
	}

	@GetMapping("/editProduct")
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

	@GetMapping("/addProduct")
	public String displayProductAddForm(Model model, @RequestParam("id") int id) {
		String title = "Add Product Form";
		Product product = new Product();
		Date today = new Date();
		product.setDateAdded(today);
		List<Category> allSubCategories = categoryService.findSubCategory();
		List<Supplier> allSuppliers = supplierService.findAllSuppliers();

		model.addAttribute("product", product);
		model.addAttribute("allSubCategories", allSubCategories);
		model.addAttribute("allSuppliers", allSuppliers);
		model.addAttribute("title", title);
		return "product-form";
	}

	@PostMapping("/product/{id}")
	public String saveProductWithId(Model model, @PathVariable("id") int id,
			@ModelAttribute("product") Product product, RedirectAttributes rdAttr) {
		String title = "Admin Panel";
		String tableDdisplay = "none";
		if (product.getThumbnail().trim().equals(""))
			product.setThumbnail("default");
		if (product.getName().trim().equals(""))
			product.setName("Tet Product " + (int) (Math.random() * 100));
		if (product.getDescription().trim().equals(""))
			product.setDescription(product.getName() + "description");

		productService.saveProduct(product);
		String redirect = "redirect:/admin/list?type=product";
		String redirectMessage = "Product " + product.getName() + " (ID: " + product.getIdProduct()
				+ ") added or updated.";

		model.addAttribute("title", title);
		model.addAttribute("table_display", tableDdisplay);
		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}
	
	@GetMapping("/delete/product")
	public String deleteProduct(@RequestParam("id") int id, RedirectAttributes rdAttr) {
		String redirect = "redirect:/admin/list?type=product";
		String redirectMessage = "Product with ID: " + id + " deleted!";
		productService.deleteProductWithId(id);
		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}
	
	@GetMapping("/delete/user")
	public String deleteUser(@RequestParam("id") String username, RedirectAttributes rdAttr) {
		String redirect = "redirect:/admin/list?type=user";
		String redirectMessage = "User " + username + " & associated adress, orders, products in cart deleted!";
		userService.deleteUserWithID(username);
		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}

	@GetMapping("/delete/order")
	public String deleteOrder(@RequestParam("id") int id, RedirectAttributes rdAttr) {
		String redirect = "redirect:/admin/list?type=order";
		String redirectMessage = "Order with ID: " + id + " deleted!";
		orderService.deleteOrderWithId(id);
		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}

}
