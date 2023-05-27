package com.example.edit.bridge.impl;

import com.example.edit.beans.Tag;
import com.example.edit.bridge.TagBridge;
import com.example.edit.models.TagModel;

import java.util.List;

public class TagBridgeImpl implements TagBridge {
    @Override
    public List<Tag> findAll() {
        return TagModel.findAll();
    }

    @Override
    public List<Tag> findByindex() {
        return TagModel.findByindex();
    }

    @Override
    public boolean checkTagById(int tags_id) {
        return TagModel.checkTagById(tags_id);
    }

    @Override
    public Tag findTagById(int tags_id) {
        return TagModel.findTagById(tags_id);
    }

    @Override
    public void addTag(Tag tag) {
        TagModel.addTag(tag);
    }

    @Override
    public void updateTage(Tag tag) {
        TagModel.updateTage(tag);
    }

    @Override
    public void deleteTag(int tags_id) {
        TagModel.deleteTag(tags_id);
    }
}
