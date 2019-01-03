package com.tiny.service;

import com.tiny.dao.DaoFactory;
import com.tiny.module.Order;

public class OrderServiceImpl implements IOrderService {
    @Override
    public int saveOrder(Order order) {
        return DaoFactory.getOrderDao().saveOrder(order);
    }
}
