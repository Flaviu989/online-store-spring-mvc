package com.sda.store.service;

import java.util.List;

import com.sda.store.model.OrderItem;

public interface OrderItemService {

	void saveOrderItem(OrderItem orderItem);

	List<OrderItem> findProductsInCart(String username);

	OrderItem findOrderItemWithId(int id);

	void saveOrderItemWithId(OrderItem orderItem);

	void saveOrderItemList(List<OrderItem> orderItemes);

	void deleteOrderItemWithId(int id);

}
