package com.example.edit.bridge.impl;

import com.example.edit.beans.Tag;
import com.example.edit.beans.Tags_articles;
import com.example.edit.bridge.TagArticleBridge;
import com.example.edit.models.TagArticleModel;

public class TagArticleBridgeImpl implements TagArticleBridge {
    @Override
    public void addTagArticle(Tags_articles tagsArt) {
        TagArticleModel.addTagArticle(tagsArt);
    }

    @Override
    public Tag findTagByTagName(String value) {
        return TagArticleModel.findTagByTagName(value);
    }

    @Override
    public Tags_articles findTagArtById(int tags_id) {
        return TagArticleModel.findTagArtById(tags_id);
    }

    @Override
    public void deleteTagArt(int tags_id) {
        TagArticleModel.deleteTagArt(tags_id);
    }

    @Override
    public void DeleteTagByArt(int article_id) {
        TagArticleModel.DeleteTagByArt(article_id);
    }
}
