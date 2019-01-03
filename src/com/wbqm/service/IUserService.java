package com.wbqm.service;

import java.util.List;
import com.wbqm.module.User;

public interface IUserService {

	List<User> find();

	void addUser(User user);

	void updateUser(User user);

	User findById(int id);

	User findByEMail(String email);
}
