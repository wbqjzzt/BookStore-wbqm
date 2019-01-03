package com.tiny.test;

import com.tiny.dao.DaoFactory;

import com.tiny.module.Book;
import com.tiny.module.User;
import com.tiny.service.ServiceFactory;
import org.junit.jupiter.api.Test;

class UserDaoImplTest {

	@Test
	void testWithEmail() throws Exception {
		User user = DaoFactory.getUserDao().findByEMail("hjianrui1@gmail.com");
		System.out.println(user.getNickName());
	}

	@Test
	void testWidthId() throws Exception {
        User user = DaoFactory.getUserDao().findById(1);
        System.out.println(user.getNickName());
    }

    @Test
    void testBook() throws Exception {
        Book book = ServiceFactory.getProductService().findBookById(1);
        System.out.println(book.getAuthorSummary());
        System.out.println(book.getCatalogue());
        System.out.println(book.getPrintNumber());
        System.out.println(book.getPrintTime());
    }

}
