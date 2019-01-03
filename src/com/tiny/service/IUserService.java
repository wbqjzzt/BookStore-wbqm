package com.tiny.service;

import java.util.List;
import com.tiny.module.User;

public interface IUserService {

	List<User> find();

	void addUser(User user);

	void updateUser(User user);

	User findById(int id);

	User findByEMail(String email);
}
