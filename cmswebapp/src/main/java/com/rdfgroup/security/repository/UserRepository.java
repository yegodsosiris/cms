package com.rdfgroup.security.repository;

import java.util.List;

import com.rdfgroup.security.domain.User;

public interface UserRepository {

	List<User> getUsers();

	User getUser(String userName);

	User createUser(User user);

	User updateUser(User user);

	void deleteUser(String id);

}
