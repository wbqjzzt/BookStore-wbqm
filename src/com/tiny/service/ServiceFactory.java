package com.tiny.service;

public class ServiceFactory {

    public static ICategoryService getCategoryService() { return new CategoryServiceImpl(); }

    public static IUserService getUserService() {
        return new UserServiceImpl();
    }

    public static IProductService getProductService() { return new ProductServiceImpl(); }

    public static IAddressService getAddressService() { return new AddressServiceImpl(); }

    public static IOrderService getOrderService() { return new OrderServiceImpl(); }

}
