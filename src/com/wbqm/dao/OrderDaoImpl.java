package com.wbqm.dao;

import com.wbqm.module.Order;
import com.wbqm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDaoImpl implements IOrderDao {

    @Override
    public int saveOrder(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getStatus());
            statement.setLong(3, order.getOrderTime());
            statement.setString(4, order.getOrderDesc());
            statement.setDouble(5, order.getTotalPrice());
            statement.setString(6, order.getReceiveName());
            statement.setString(7, order.getFullAddress());
            statement.setString(8, order.getPostalCode());
            statement.setString(9, order.getMobile());
            statement.setString(10, order.getPhone());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) return resultSet.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement);
        }
        return -1;
    }

}
