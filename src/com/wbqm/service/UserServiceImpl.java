package com.wbqm.service;

import java.util.List;

import com.wbqm.dao.DaoFactory;
import com.wbqm.module.User;

public class UserServiceImpl implements IUserService {

	@Override
	public List<User> find() { return DaoFactory.getUserDao().findAll(); }

	@Override
	public void addUser(User user) {
		DaoFactory.getUserDao().addUser(user);
	}

	@Override
	public void updateUser(User user) {
		DaoFactory.getUserDao().updateUser(user);
	}

	@Override
	public User findById(int id) { return DaoFactory.getUserDao().findById(id); }

	@Override
	public User findByEMail(String email) {
		return DaoFactory.getUserDao().findByEMail(email);
	}
}
