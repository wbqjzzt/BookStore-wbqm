package com.wbqm.dao;

import com.wbqm.module.Category;

import java.util.List;

public interface ICategoryDao {

    String FIND_ALL_CATE = "SELECT * FROM d_category";

    String FIND_CATE_BY_PARENT_ID = "SELECT dc.*, COUNT(dcp.product_id) "
            + "AS pNum FROM d_category dc LEFT OUTER JOIN d_category_product dcp ON dc.id=dcp"
            + ".cat_id WHERE dc.parent_id=? GROUP BY dc.id";

    List<Category> findAllCategory();

//    List<Category> findCategoryByParentID(int parentId);

}
