package com.example.edit.builder.impl;

import com.example.edit.beans.Feedback;
import com.example.edit.builder.IFeedbackBuilder;

public class FeedbackBuilder implements IFeedbackBuilder {
    private int feedback_id;
    private int editor_id;
    private int article_id;
    private String description;

    private String title;
    private String categoryName;
    private int status_id;
    private String second_name;

    private int writer_id;

    @Override
    public IFeedbackBuilder feedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
        return this;
    }

    @Override
    public IFeedbackBuilder editor_id(int editor_id) {
        this.editor_id = editor_id;
        return this;
    }

    @Override
    public IFeedbackBuilder article_id(int article_id) {
        this.article_id = article_id;
        return this;
    }

    @Override
    public IFeedbackBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public IFeedbackBuilder title(String title) {
        this.title =title;
        return this;
    }

    @Override
    public IFeedbackBuilder categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public IFeedbackBuilder status_id(int status_id) {
        this.status_id =status_id;
        return this;
    }

    @Override
    public IFeedbackBuilder second_name(String second_name) {
        this.second_name = second_name;
        return this;
    }

    @Override
    public IFeedbackBuilder writer_id(int writer_id) {
        this.writer_id =writer_id;
        return this;
    }

    @Override
    public Feedback build() {
        return new Feedback(feedback_id, editor_id,article_id,description,
                title, categoryName, status_id, second_name, writer_id);
    }
}
