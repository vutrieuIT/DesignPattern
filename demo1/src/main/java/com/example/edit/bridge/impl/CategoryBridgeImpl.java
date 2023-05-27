package com.example.edit.bridge.impl;

import com.example.edit.beans.Category;
import com.example.edit.bridge.CategoryBridge;
import com.example.edit.models.CategoryModel;

import java.util.List;

public class CategoryBridgeImpl implements CategoryBridge {
    @Override
    public List<Category> findAll() {
        return CategoryModel.findAll();
    }

    @Override
    public List<Category> findAllIn() {
        return CategoryModel.findAllIn();
    }

    @Override
    public List<Category> findByParentId(int parent_id) {
        return CategoryModel.findByParentId(parent_id);
    }

    @Override
    public List<Category> find5Cate() {
        return CategoryModel.find5Cate();
    }

    @Override
    public List<Category> find4Cate() {
        return CategoryModel.find4Cate();
    }

    @Override
    public List<Category> getCateChilds(int categories_id) {
        return CategoryModel.getCateChilds(categories_id);
    }

    @Override
    public boolean checkCate(int cateId) {
        return CategoryModel.checkCate(cateId);
    }

    @Override
    public List<Category> getCateByID(int cateId) {
        return CategoryModel.getCateByID(cateId);
    }

    @Override
    public Category findCateById(int categories_id) {
        return CategoryModel.findCateById(categories_id);
    }

    @Override
    public void addCate(Category category) {
        CategoryModel.addCate(category);
    }

    @Override
    public void addCateCon(Category category) {
        CategoryModel.addCateCon(category);
    }

    @Override
    public void updateCate(Category category) {
        CategoryModel.updateCate(category);
    }

    @Override
    public void deleteCate(int categories_id) {
        CategoryModel.deleteCate(categories_id);
    }

    @Override
    public void deleteCateCon(int parent_id) {
        CategoryModel.deleteCateCon(parent_id);
    }

    @Override
    public Category findCatByName(String name) {
        return CategoryModel.findCatByName(name);
    }

    @Override
    public List<Category> findAllCat() {
        return CategoryModel.findAllCat();
    }

    @Override
    public List<Category> findCatCon(int parent_id) {
        return CategoryModel.findCatCon(parent_id);
    }
}
