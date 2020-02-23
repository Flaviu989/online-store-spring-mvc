package com.sda.store.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sda.store.model.User;
import com.sda.store.repository.UserRepository;
import com.sda.store.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserByName(String username) {
		return userRepository.findByUsernameContaining(username);
	}
}
