package com.tiny.dao;

import com.tiny.module.Category;
import com.tiny.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {

    @Override
    public List<Category> findAllCategory() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(FIND_ALL_CATE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setParentId(resultSet.getInt("parent_id"));
                category.setEnName(resultSet.getString("en_name"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }

        return categories;
    }

}
