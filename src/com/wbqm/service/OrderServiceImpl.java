package com.wbqm.service;

import com.wbqm.dao.DaoFactory;
import com.wbqm.module.Order;

public class OrderServiceImpl implements IOrderService {
    @Override
    public int saveOrder(Order order) {
        return DaoFactory.getOrderDao().saveOrder(order);
    }
}
