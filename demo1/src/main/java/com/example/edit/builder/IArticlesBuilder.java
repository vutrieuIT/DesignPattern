package com.example.edit.builder;

import com.example.edit.beans.Articles;

import java.util.Date;

public interface IArticlesBuilder {
    IArticlesBuilder article_id(int article_id);
    IArticlesBuilder title(String title);
    IArticlesBuilder create_date(Date create_date);
    IArticlesBuilder publish_date(Date publish_date);
    IArticlesBuilder views(int views);
    IArticlesBuilder abstracts(String abstracts);
    IArticlesBuilder content(String content);
    IArticlesBuilder categories_id(int categories_id);
    IArticlesBuilder premium(boolean premium);
    IArticlesBuilder writer_id(int writer_id);
    IArticlesBuilder status_id(int status_id);
    IArticlesBuilder avatar(String avatar);
    IArticlesBuilder categoryName(String categoryName);
    IArticlesBuilder second_name(String second_name);
    IArticlesBuilder value(String value);
    IArticlesBuilder editor_id(int editor_id);

    Articles build();
}
