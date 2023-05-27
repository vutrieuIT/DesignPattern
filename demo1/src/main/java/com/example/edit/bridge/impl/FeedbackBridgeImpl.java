package com.example.edit.bridge.impl;

import com.example.edit.beans.Feedback;
import com.example.edit.bridge.FeedbackBridge;
import com.example.edit.models.FeedbackModel;

import java.util.List;

public class FeedbackBridgeImpl implements FeedbackBridge {
    @Override
    public void addFeedback(Feedback feedback) {
        FeedbackModel.addFeedback(feedback);
    }

    @Override
    public boolean checkFeedback(int writer_id) {
        return FeedbackModel.checkFeedback(writer_id);
    }

    @Override
    public List<Feedback> findFeedback(int writer_id) {
        return FeedbackModel.findFeedback(writer_id);
    }

    @Override
    public void DeleteFeedByAId(int article_id) {
        FeedbackModel.DeleteFeedByAId(article_id);
    }
}
