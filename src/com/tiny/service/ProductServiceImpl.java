package com.tiny.service;

import com.tiny.dao.DaoFactory;
import com.tiny.module.Book;

import java.util.List;

public class ProductServiceImpl implements IProductService {

    @Override
    public Book findBookById(int id) {
        return DaoFactory.getProductDao().findBookById(id);
    }

    @Override
    public List<Book> findBookWithHot(int limit) { return DaoFactory.getProductDao().findBookWithHot(limit); }

    @Override
    public List<Book> findNewBook(int limit) { return DaoFactory.getProductDao().findNewBook(limit); }
}
