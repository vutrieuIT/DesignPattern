package com.example.edit.bridge.impl;

import com.example.edit.beans.Editor_manage_categories;
import com.example.edit.bridge.EditorManageBridge;
import com.example.edit.models.EditorManageModel;

import java.util.List;

public class EditorManageBridgeImpl implements EditorManageBridge {
    @Override
    public void addBTVCate(Editor_manage_categories c) {
        EditorManageModel.addBTVCate(c);
    }

    @Override
    public List<Editor_manage_categories> listCateByIdUser(int editor_id) {
        return EditorManageModel.listCateByIdUser(editor_id);
    }

    @Override
    public void DeleteEditorManage(int id) {
        EditorManageModel.DeleteEditorManage(id);
    }

    @Override
    public void DeleteByUserId(int editor_id) {
        EditorManageModel.DeleteByUserId(editor_id);
    }

    @Override
    public void DeleteByCate(int category_id) {
        EditorManageModel.DeleteByCate(category_id);
    }
}
