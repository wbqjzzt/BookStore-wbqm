package com.tiny.dao;

import com.tiny.module.User;
import java.util.List;

public interface IUserDao {

	String SELECT_USER = "SELECT * FROM d_user";

	String ADD_USER =
			"INSERT INTO d_user (email, nickname, password, user_integral, " +
					"is_email_verify, email_verify_code, last_login_time, " +
                    "last_login_ip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	String UPDATE_USER =
			"UPDATE d_user SET email = ?, nickname = ?, user_integral = ?, " +
                    "is_email_verify = ?, email_verify_code = ?, last_login_time" +
                    " = ?, last_login_ip = ? WHERE id = ?";

	String FIND_BY_ID = "SELECT * FROM d_user WHERE id = ?";

	String FIND_BY_EMAIL = "SELECT * FROM d_user WHERE email = ?";

	List<User> findAll();

	void addUser(User user);

	void updateUser(User user);

	User findById(int id);

	User findByEMail(String email);

}
