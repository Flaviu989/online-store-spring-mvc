package com.sda.store.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.store.model.Order;
import com.sda.store.model.OrderItem;
import com.sda.store.service.OrderItemService;
import com.sda.store.service.OrderService;
import com.sda.store.service.ProductService;
import com.sda.store.service.StatusService;
import com.sda.store.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private StatusService statusService;

	private int totalPrice;

	@PostMapping("/product/{id}")
	public String saveOrderItem(Model model, @PathVariable("id") int id,
			@ModelAttribute("orderItem") OrderItem orderItem, Principal user, RedirectAttributes rdAttr) {
		orderItem.setProduct(productService.findProductByIdProduct(id));
		orderItem.setUser(userService.findUserByName(user.getName()));
		orderItem.setProductPrice(productService.findProductByIdProduct(id).getItemPrice());

		orderItem = checkForDuplicate(orderItem, user);

		orderItemService.saveOrderItem(orderItem);
		String redirect = "redirect:/product/" + id;
		String redirectMessage = "Product" + orderItem.getProduct().getName() + " added to cart.";

		rdAttr.addFlashAttribute("message", redirectMessage);
		return redirect;
	}

	private OrderItem checkForDuplicate(OrderItem orderItem, Principal user) {
		OrderItem[] original = { orderItem };
		List<OrderItem> checkList = orderItemService.findProductsInCart(user.getName());
		checkList.stream().forEach(oI -> {
			if (oI.getProduct().getIdProduct() == original[0].getProduct().getIdProduct()) {
				original[0].setQuantity(original[0].getQuantity() + oI.getQuantity());
				orderItemService.deleteOrderItemWithId(oI.getIdOrderItem());
			}
		});
		return original[0];
	}

	@GetMapping("/cart")
	public String displayCart(Model model, @RequestParam("edit") int editId, @RequestParam("remove") int removeId,
			Principal user) {
		String display = "none";
		if (user != null) {
			String username = user.getName();
			List<OrderItem> orderItems = orderItemService.findProductsInCart(username);
			if (editId != 0) {
				display = "edit";
				model.addAttribute("ordItem", orderItemService.findOrderItemWithId(editId));
			}
			if (removeId != 0) {
				orderItemService.deleteOrderItemWithId(removeId);
				orderItems = orderItemService.findProductsInCart(username);
			}
			Order order = new Order();

			model.addAttribute("orderItems", orderItems);
			model.addAttribute("userName", username);
			model.addAttribute("order", order);
		}

		model.addAttribute("display", display);
		return "cart";
	}

	@PostMapping("/cart")
	public String editCart(Model model, @RequestParam() int edit, @RequestParam() int remove,
			@ModelAttribute("ordItem") OrderItem orderItem, Principal user) {
		orderItem.setIdOrderItem(orderItemService.findOrderItemWithId(edit).getIdOrderItem());
		orderItem.setProduct(orderItemService.findOrderItemWithId(edit).getProduct());
		orderItem.setUser(orderItemService.findOrderItemWithId(edit).getUser());

		orderItemService.saveOrderItemWithId(orderItem);
		return "redirect:/cart?edit=0&remove=0";
	}
	
	@GetMapping("/orders")
	public String displayOrders(Model model, Principal user) {
		model.addAttribute("orders", orderService.findOrdersFromUser(user.getName()));
		model.addAttribute("userName", user.getName());
		return "order";
	}

	@Transactional
	@PostMapping("/orders")
	public String saveOrder(Model model, @ModelAttribute("order") Order order, Principal user) {
		String username = user.getName();
		orderService.saveOrder(getOrderInProgress(order, user));

		List<OrderItem> orderItemes = orderItemService.findProductsInCart(username);
		orderItemes.forEach(oI -> oI.setOrder(order));

		orderService.saveOrder(getPlacedOrder(order, orderItemes));
		orderItemService.saveOrderItemList(orderItemes);

		return "redirect:/orders";
	}

	private Order getOrderInProgress(Order order, Principal user) {
		order.setDateOfOrder(new Date());
		order.setUser(userService.findUserByName(user.getName()));
		order.setStatus(statusService.findStatus(1));
		return order;
	}

	private Order getPlacedOrder(Order order, List<OrderItem> orderItemes) {
		order.setOrderItems(orderItemes);
		double totalPrice = orderItemes.stream().mapToDouble(oI -> oI.getProductPrice() * oI.getQuantity()).sum();
		order.setTotalCost(totalPrice);
		order.setStatus(statusService.findStatus(2));
		return order;
	}

}
