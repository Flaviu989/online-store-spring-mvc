package com.sda.store.service;

import com.sda.store.model.Address;

public interface AddressService {

	Address findAddressOfUser(String username);

	void saveAddress(Address address);

}
