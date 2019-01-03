package com.tiny.service;

import com.tiny.module.Book;

import java.util.List;

public interface IProductService {

    Book findBookById(int id);

    List<Book> findBookWithHot(int limit);

    List<Book> findNewBook(int limit);

}
