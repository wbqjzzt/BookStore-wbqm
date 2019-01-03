package com.tiny.dao;

import com.tiny.module.Order;

import java.util.List;

public interface IOrderDao {

    String SAVE_ORDER = "INSERT INTO d_order (user_id, status, order_time," +
            " order_desc, total_price, receive_name, full_address, postal_code," +
            " mobile, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    int saveOrder(Order order);

}
