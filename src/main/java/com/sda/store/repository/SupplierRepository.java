package com.sda.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sda.store.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	List<Supplier> findByOrderByNameAsc();

}
