package com.wbqm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wbqm.module.User;
import com.wbqm.util.DBUtil;

public class UserDaoImpl implements IUserDao {

	public User findById(int id) {
	    PreparedStatement statement = null;
	    Connection connection = null;
	    ResultSet resultSet = null;
	    User user = null;
	    try {
	        connection = DBUtil.getConnection();
	        statement = connection.prepareStatement(FIND_BY_ID);
	        statement.setInt(1, id);
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            user = new User();
	            user.setId(resultSet.getInt("id"));
	            user.setEmail(resultSet.getString("email"));
	            user.setNickName(resultSet.getString("nickname"));
	            user.setPassword(resultSet.getString("password"));
	            user.setUserIntegral(resultSet.getInt("user_integral"));

	            user.setEmailVerified((resultSet.getString("is_email_verify")
                        .equalsIgnoreCase("T")));

	            user.setEmailVerifyCode(resultSet.getString("email_verify_code"));
	            user.setLastLoginTime(resultSet.getLong("last_login_time"));
	            user.setLastLoginIp(resultSet.getString("last_login_ip"));
            }
        } catch (Exception e) {
	        e.printStackTrace();
        } finally {
	        DBUtil.close(resultSet, connection, statement);
        }
        return user;
    }

    public User findByEMail(String email) {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setNickName(resultSet.getString("nickname"));
                user.setPassword(resultSet.getString("password"));
                user.setUserIntegral(resultSet.getInt("user_integral"));

                user.setEmailVerified((resultSet.getString("is_email_verify")
                        .equalsIgnoreCase("T")));

                user.setEmailVerifyCode(resultSet.getString("email_verify_code"));
                user.setLastLoginTime(resultSet.getLong("last_login_time"));
                user.setLastLoginIp(resultSet.getString("last_login_ip"));
            } else {
            	return null;
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }
        return user;
    }


	public void updateUser(User user) {
	    PreparedStatement statement = null;
	    Connection connection = null;
	    try {
	        connection = DBUtil.getConnection();
	        statement = connection.prepareStatement(UPDATE_USER);
	        statement.setString(1, user.getEmail());
	        statement.setString(2, user.getNickName());
	        statement.setInt(3, user.getUserIntegral());
            if ((user.getEmailVerified())) {
                statement.setString(4, "T");
            } else {
                statement.setString(4, "F");
            }
            statement.setString(5, user.getEmailVerifyCode());
            statement.setLong(6, user.getLastLoginTime());
            statement.setString(7, user.getLastLoginIp());
            statement.setInt(8, user.getId());
            statement.executeUpdate();

        } catch (Exception e) {
	        e.printStackTrace();
        } finally {
	        DBUtil.close(connection, statement);
        }
    }

	public void addUser(User user) {
	    PreparedStatement statement = null;
	    Connection connection = null;
	    try {
	        connection = DBUtil.getConnection();
	        statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
	        statement.setString(1, user.getEmail());
	        statement.setString(2, user.getNickName());
	        statement.setString(3, user.getPassword());
	        statement.setInt(4, user.getUserIntegral());
            if ((user.getEmailVerified())) {
                statement.setString(5, "T");
            } else {
                statement.setString(5, "F");
            }
            statement.setString(6, user.getEmailVerifyCode());
            statement.setLong(7, user.getLastLoginTime());
            statement.setString(8, user.getLastLoginIp());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) user.setId(resultSet.getInt(1));

        } catch (SQLException exception) {
	        exception.printStackTrace();
        } finally {
	        DBUtil.close(connection, statement);
        }
	}

	@SuppressWarnings("finally")
	@Override
	public List<User> findAll() {

		Connection conn = null;
		ResultSet result = null;
		Statement statement = null;

		List<User> users = null;
		User user = new User();

		try {
			conn = DBUtil.getConnection();

			statement = conn.createStatement();
			result = statement.executeQuery(SELECT_USER);
			users = new ArrayList<> ();

			while (result.next()) {

				user.setId(result.getInt("id"));
				user.setUserIntegral(result.getInt("user_integral"));
				user.setEmail(result.getString("email"));
				user.setNickName(result.getString("nickname"));
				user.setPassword(result.getString("password"));
				user.setEmailVerified(result.getBoolean("is_email_verify"));
				user.setEmailVerifyCode(result.getString("email_verify_code"));
				user.setLastLoginTime(result.getLong("last_login_time"));
				user.setLastLoginIp(result.getString("last_login_ip"));

				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(result, conn, statement);
		}
		return users;

	}
}
