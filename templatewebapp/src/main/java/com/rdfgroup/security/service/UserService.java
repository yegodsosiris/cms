package com.rdfgroup.security.service;

import java.util.List;

import com.rdfgroup.security.domain.User;

public interface UserService {

	List<User> getUsers();

	User getUserByUsernameAndPassord(String userName, String password);

	User createUser(User user);

	User updateUser(User user);

	void deleteUser(String id);

    User getUserByUsername(String username);

}
