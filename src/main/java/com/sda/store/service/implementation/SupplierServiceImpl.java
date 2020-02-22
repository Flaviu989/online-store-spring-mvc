package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Supplier;
import com.sda.store.repository.SupplierRepository;
import com.sda.store.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> findAllSuppliers() {
		return supplierRepository.findByOrderByNameAsc();
	}
}
