package com.rdfgroup.security.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rdfgroup.cms.services.repository.BaseRepository;
import com.rdfgroup.security.domain.User;

@Repository
public class UserRepositoryImpl extends BaseRepository implements UserRepository {

	@Override
	public List<User> getUsers() {
		return findAll(User.class);
	}

	@Override
	public User getUser(String userName) {
		return getOne("username", userName, User.class);
	}

	@Override
	public User createUser(User user) {
		return (User) insert(user);
	}

	@Override
	public User updateUser(User user) {
		return (User) save(user);
	}

	@Override
	public void deleteUser(String id) {
	    delete(id, User.class);
	}

}
