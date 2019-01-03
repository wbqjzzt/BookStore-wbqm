package com.wbqm.service;

import com.wbqm.dao.DaoFactory;
import com.wbqm.module.Category;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService{

    @Override
    public List<Category> findAllCategory() {
        return DaoFactory.getCategoryDao().findAllCategory();
    }
}
