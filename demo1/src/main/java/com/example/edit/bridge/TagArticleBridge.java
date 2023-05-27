package com.example.edit.bridge;

import com.example.edit.Utils.DbUtils;
import com.example.edit.beans.Tag;
import com.example.edit.beans.Tags_articles;
import org.sql2o.Connection;

import java.util.List;

public interface TagArticleBridge {
    void addTagArticle(Tags_articles tagsArt);

    Tag findTagByTagName(String value);

    Tags_articles findTagArtById(int tags_id);

    void deleteTagArt(int tags_id);

    void DeleteTagByArt(int article_id);
}
