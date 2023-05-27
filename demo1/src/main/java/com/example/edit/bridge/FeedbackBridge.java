package com.example.edit.bridge;

import com.example.edit.beans.Feedback;

import java.util.List;

public interface FeedbackBridge {
    void addFeedback(Feedback feedback);

    boolean checkFeedback(int writer_id);

    List<Feedback> findFeedback(int writer_id);

    void DeleteFeedByAId(int article_id);
}
