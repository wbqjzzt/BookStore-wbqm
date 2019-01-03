package com.tiny.dao;

public class DaoFactory {

    public static ICategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    public static IUserDao getUserDao() { return new UserDaoImpl(); }

    public static IProductDao getProductDao() { return new ProductDaoImpl(); }

    public static IAddressDao getAddressDao() { return new AddressDaoImpl(); }

    public static IOrderDao getOrderDao() { return new OrderDaoImpl(); }

}
