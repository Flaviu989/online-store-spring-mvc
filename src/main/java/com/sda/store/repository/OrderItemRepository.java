package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderNullAndUserUsername(String username);

	List<OrderItem> findByUserUsername(String username);

	OrderItem findByIdOrderItem(int id);

}
