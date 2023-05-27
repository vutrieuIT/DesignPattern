package com.example.edit.bridge;

import com.example.edit.beans.Tag;

import java.util.List;

public interface TagBridge {
    //Tìm tất cả các Tag
    List<Tag> findAll();

    //Tìm Tag theo index
    List<Tag> findByindex();

    boolean checkTagById(int tags_id);

    Tag findTagById(int tags_id);

    void addTag(Tag tag);

    void updateTage(Tag tag);

    void deleteTag(int tags_id);
}
