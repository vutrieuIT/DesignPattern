package com.example.edit.bridge;

import com.example.edit.beans.Category;

import java.util.List;

public interface CategoryBridge {
    //Tìm tất cả danh mục không có parentId
    List<Category> findAll();

    List<Category> findAllIn();

    // Tìm danh mục con của một danh mục
    List<Category> findByParentId(int parent_id);

    List<Category> find5Cate();

    List<Category> find4Cate();

    // Tìm danh mục con của một danh mục
    List<Category> getCateChilds(int categories_id);

    //Check xem có Category con ko
    boolean checkCate(int cateId);

    //Lấy danh mục theo category Id
    List<Category> getCateByID(int cateId);

    Category findCateById(int categories_id);

    void addCate(Category category);

    void addCateCon(Category category);

    void updateCate(Category category);

    void deleteCate(int categories_id);

    void deleteCateCon(int parent_id);

    Category findCatByName(String name);

    List<Category> findAllCat();

    List<Category> findCatCon(int parent_id);
}
