package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.repository.SupplierRepository;
import com.sda.store.service.StatusService;

@Service
public class SupplierServiceImpl implements StatusService {

	@Autowired
	private SupplierRepository supplierRepository;
}
