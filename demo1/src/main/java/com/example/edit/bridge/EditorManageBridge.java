package com.example.edit.bridge;

import com.example.edit.beans.Editor_manage_categories;

import java.util.List;

public interface EditorManageBridge {
    void addBTVCate(Editor_manage_categories c);

    List<Editor_manage_categories> listCateByIdUser(int editor_id);

    void DeleteEditorManage(int id);

    void DeleteByUserId(int editor_id);

    void DeleteByCate(int category_id);
}
