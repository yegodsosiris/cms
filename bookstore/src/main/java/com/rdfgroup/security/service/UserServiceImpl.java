package com.rdfgroup.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfgroup.security.domain.User;
import com.rdfgroup.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User getUserByUsernameAndPassord(String userName, String password) {
		User user = userRepository.getUser(userName);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User createUser(User user) {
		return userRepository.createUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public void deleteUser(String id) {
		userRepository.deleteUser(id);
	}

    @Override public User getUserByUsername(String username) {
        return userRepository.getUser(username);
    }

}
