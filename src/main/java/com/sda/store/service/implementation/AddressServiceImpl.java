package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.Address;
import com.sda.store.repository.AddressRepository;
import com.sda.store.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address findAddressOfUser(String username) {
		return addressRepository.findByUserUsername(username);
	}

	@Override
	public void saveAddress(Address address) {
		addressRepository.save(address);
	}


}
