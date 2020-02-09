package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.store.repository.AddressRepository;
import com.sda.store.service.AddressService;

public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

}
