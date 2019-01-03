package com.wbqm.servlet;

import com.wbqm.module.Book;
import com.wbqm.module.Category;
import com.wbqm.service.IProductService;
import com.wbqm.service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String method = request.getParameter("method");

        Map<String, List> categories = new HashMap<>();
        if (null != method) {

            switch (method) {
                case "1" : productDetails(request, response); break;

                default: break;
            }
        } else {

            classification(categories);

            recommendation(categories);

            newBooks(categories);

            hostBooks(categories);

            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/view/home/main.jsp").forward(request, response);
        }
    }

    private void productDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            System.out.println(id);
            Book book = ServiceFactory.getProductService().findBookById(id);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/view/home/product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hostBooks(Map<String, List> data) {
        List<Book> books = ServiceFactory.getProductService().findBookWithHot(4);

//        System.out.println("Hot Books");
        data.put("hotBooks", books);
    }

    private void newBooks(Map<String, List> data) {
        List<Book> books = ServiceFactory.getProductService().findNewBook(4);

        data.put("newBooks", books);
    }

    private void recommendation(Map<String, List> data) {
        List<Book> bookList = new ArrayList<>();
        IProductService productService = ServiceFactory.getProductService();

        bookList.add(productService.findBookById((int) (Math.random() * 20) + 1));
        bookList.add(productService.findBookById((int) (Math.random() * 20) + 1));
         data.put("books", bookList);
    }

    private void classification(Map<String, List> categories) {
        List<Category> categoryList = ServiceFactory.getCategoryService().findAllCategory();
        List<Category> baseCategories = sortCategories(categoryList, 1);

        for (Category category : baseCategories) {
            category.setSubCategory(sortCategories(categoryList, category.getId()));
        }
        categories.put("category", baseCategories);
    }

    private List<Category> sortCategories(List<Category> categories, int id) {
        List<Category> sortCategories = new ArrayList<>();
        for (Category category : categories) {
            if (id == category.getParentId()) {
                sortCategories.add(category);
            }
        }
        return sortCategories;
    }
}
