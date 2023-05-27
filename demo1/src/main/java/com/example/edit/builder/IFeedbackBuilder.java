package com.example.edit.builder;

import com.example.edit.beans.Feedback;

public interface IFeedbackBuilder {
    IFeedbackBuilder feedback_id(int feedback_id);
    IFeedbackBuilder editor_id(int editor_id);
    IFeedbackBuilder article_id(int article_id);
    IFeedbackBuilder description(String description);

    IFeedbackBuilder title(String title);
    IFeedbackBuilder categoryName(String categoryName);
    IFeedbackBuilder status_id(int status_id);
    IFeedbackBuilder second_name(String second_name);

    IFeedbackBuilder writer_id(int writer_id);

    Feedback build();
}
