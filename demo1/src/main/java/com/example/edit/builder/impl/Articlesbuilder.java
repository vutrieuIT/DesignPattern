package com.example.edit.builder.impl;

import com.example.edit.beans.Articles;
import com.example.edit.builder.IArticlesBuilder;

import java.util.Date;

public class Articlesbuilder implements IArticlesBuilder {
    private int article_id;
    private String title;
    private Date create_date;
    private Date publish_date;
    private int views;
    private String abstracts;
    private String content;
    private int categories_id;
    private boolean premium;
    private int writer_id;
    private int status_id;
    private String avatar;
    private String categoryName;
    private String second_name;
    private String value;
    private int editor_id;

    @Override
    public IArticlesBuilder article_id(int article_id) {
        this.article_id = article_id;
        return this;
    }

    @Override
    public IArticlesBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public IArticlesBuilder create_date(Date create_date) {
        this.create_date = create_date;
        return this;
    }

    @Override
    public IArticlesBuilder publish_date(Date publish_date) {
        this.publish_date = publish_date;
        return this;
    }

    @Override
    public IArticlesBuilder views(int views) {
        this.views = views;
        return this;
    }

    @Override
    public IArticlesBuilder abstracts(String abstracts) {
        this.abstracts = abstracts;
        return this;
    }

    @Override
    public IArticlesBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public IArticlesBuilder categories_id(int categories_id) {
        this.categories_id = categories_id;
        return this;
    }

    @Override
    public IArticlesBuilder premium(boolean premium) {
        this.premium = premium;
        return this;
    }

    @Override
    public IArticlesBuilder writer_id(int writer_id) {
        this.writer_id = writer_id;
        return this;
    }

    @Override
    public IArticlesBuilder status_id(int status_id) {
        this.status_id = status_id;
        return this;
    }

    @Override
    public IArticlesBuilder avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public IArticlesBuilder categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public IArticlesBuilder second_name(String second_name) {
        this.second_name = second_name;
        return this;
    }

    @Override
    public IArticlesBuilder value(String value) {
        this.value = value;
        return this;
    }

    @Override
    public IArticlesBuilder editor_id(int editor_id) {
        this.editor_id = editor_id;
        return this;
    }

    @Override
    public Articles build() {
        return new Articles(article_id, title, create_date, publish_date, views, abstracts,
                content, categories_id, premium, writer_id, status_id,
                avatar, categoryName, second_name, editor_id);
    }
}
