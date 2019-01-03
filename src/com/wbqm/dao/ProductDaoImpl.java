package com.wbqm.dao;

import com.wbqm.module.Book;
import com.wbqm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {

    @Override
    public List<Book> findBookWithHot(int limit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(FIND_BOOK_WITH_HOT);
            statement.setInt(1, limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishing(resultSet.getString("publishing"));
                book.setPublishTime(resultSet.getLong("publish_time"));
                book.setWordNumber(resultSet.getString("word_number"));
                book.setWhichEdition(resultSet.getString("which_edtion"));
                book.setTotalPage(resultSet.getString("total_page"));
                book.setPrintTime(resultSet.getLong("print_time"));
                book.setPrintNumber(resultSet.getString("print_number"));
                book.setISBN(resultSet.getString("isbn"));
                book.setAuthorSummary(resultSet.getString("author_summary"));
                book.setCatalogue(resultSet.getString("catalogue"));

                book.setDescription(resultSet.getString("description"));
                book.setProductName(resultSet.getString("product_name"));
                book.setAddTime(resultSet.getLong("add_time"));
                book.setFixedPrice(resultSet.getDouble("fixed_price"));
                book.setDangPrice(resultSet.getDouble("dang_price"));
                book.setKeywords(resultSet.getString("keywords"));
                book.setDeleted((resultSet.getInt("has_deleted") != 0));
                book.setProductPicture(resultSet.getString("product_pic"));

//                System.out.println(book.getISBN());

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }

        return books;
    }

    @Override
    public List<Book> findNewBook(int limit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(FIND_NEW_BOOK);
            statement.setInt(1, limit);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishTime(resultSet.getLong("publish_time"));
                book.setPublishing(resultSet.getString("publishing"));
                book.setWordNumber(resultSet.getString("word_number"));
                book.setWhichEdition(resultSet.getString("which_edtion"));
                book.setTotalPage(resultSet.getString("total_page"));
                book.setPrintTime(resultSet.getLong("print_time"));
                book.setPrintNumber(resultSet.getString("print_number"));
                book.setISBN(resultSet.getString("isbn"));
                book.setAuthorSummary(resultSet.getString("author_summary"));
                book.setCatalogue(resultSet.getString("catalogue"));

                book.setProductName(resultSet.getString("product_name"));
                book.setDescription(resultSet.getString("description"));
                book.setAddTime(resultSet.getLong("add_time"));
                book.setFixedPrice(resultSet.getDouble("fixed_price"));
                book.setDangPrice(resultSet.getDouble("dang_price"));
                book.setKeywords(resultSet.getString("keywords"));
                book.setDeleted((resultSet.getInt("has_deleted") != 0));
                book.setProductPicture(resultSet.getString("product_pic"));

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }

        return books;
    }

    public Book findBookById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(FIND_BOOK_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishing(resultSet.getString("publishing"));
                book.setPublishTime(resultSet.getLong("publish_time"));
                book.setWordNumber(resultSet.getString("word_number"));
                book.setWhichEdition(resultSet.getString("which_edtion"));
                book.setTotalPage(resultSet.getString("total_page"));
                book.setPrintTime(resultSet.getLong("print_time"));
                book.setPrintNumber(resultSet.getString("print_number"));
                book.setISBN(resultSet.getString("isbn"));
                book.setAuthorSummary(resultSet.getString("author_summary"));
                book.setCatalogue(resultSet.getString("catalogue"));

                book.setProductName(resultSet.getString("product_name"));
                book.setDescription(resultSet.getString("description"));
                book.setAddTime(resultSet.getLong("add_time"));
                book.setFixedPrice(resultSet.getDouble("fixed_price"));
                book.setDangPrice(resultSet.getDouble("dang_price"));
                book.setKeywords(resultSet.getString("keywords"));
                book.setDeleted((resultSet.getInt("has_deleted") != 0));
                book.setProductPicture(resultSet.getString("product_pic"));


                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
