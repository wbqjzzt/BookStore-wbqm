package com.tiny.service;

import com.tiny.dao.DaoFactory;
import com.tiny.module.Category;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService{

    @Override
    public List<Category> findAllCategory() {
        return DaoFactory.getCategoryDao().findAllCategory();
    }
}
