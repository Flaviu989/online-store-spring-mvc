package com.sda.store.service;

import java.util.List;

import com.sda.store.model.User;

public interface UserService {

	List<User> findAllUsers();

	void saveUser(User user);

	User findUserByName(String username);

	void deleteUserWithID(String username);

}
