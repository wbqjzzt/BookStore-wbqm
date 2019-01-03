package com.wbqm.dao;

import com.wbqm.module.Book;

import java.util.List;

public interface IProductDao {

    String FIND_BOOK_BY_ID = "SELECT * FROM d_product p LEFT JOIN d_book b ON " +
            "b.id = p.id WHERE p.id = ?";

//    String FIND_BOOK_WITH_HOT = "SELECT *, p.product_name, SUM(it.product_num) AS " +
//            "product_num FROM d_item it LEFT JOIN d_product p ON p.id = it.product_id " +
//            "LEFT JOIN d_book b ON b.id = p.id GROUP BY it.product_id ORDER BY " +
//            "product_num DESC LIMIT ?";

    String FIND_BOOK_WITH_HOT = "SELECT SUM(product_num) AS num, p.*, b.* FROM d_item i " +
            "JOIN d_product p ON i.product_id=p.id JOIN d_book b ON i.product_id=b.id " +
            " GROUP BY product_id ORDER BY num DESC LIMIT 0,?";

//    String FIND_NEW_BOOK = "SELECT p.product_name, p.add_time FROM d_product p LEFT" +
//            " JOIN d_book b ON b.id = p.id ORDER BY p.add_time DESC LIMIT ?";

    String FIND_NEW_BOOK = "SELECT *, p.add_time FROM d_product p LEFT" +
            " JOIN d_book b ON b.id = p.id ORDER BY p.add_time DESC LIMIT 0,?";

    List<Book> findBookWithHot(int limit);

    List<Book> findNewBook(int limit);

    Book findBookById(int id);

}

//SELECT SUM(product_num) AS num, p.*, b.* FROM d_item i JOIN d_product p ON i.product_id=p.id JOIN d_book b ON i.product_id=b.id GROUP BY product_id ORDER BY num DESC LIMIT 0,4

//SELECT *, p.add_time FROM d_product p LEFT JOIN d_book b ON b.id = p.id ORDER BY p.add_time DESC LIMIT 0,4;