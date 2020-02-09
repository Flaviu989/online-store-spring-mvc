package com.sda.store.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.store.repository.UserRepository;
import com.sda.store.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
}
