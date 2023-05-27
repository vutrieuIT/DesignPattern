package com.example.edit.bridge.impl;

import com.example.edit.beans.Comments;
import com.example.edit.bridge.CommentBridge;
import com.example.edit.models.CommentModel;

public class CommentBridgeImpl implements CommentBridge {
    @Override
    public void addComment(Comments comments) {
        CommentModel.addComment(comments);
    }

    @Override
    public void DeleteCmtByArtId(int article_id) {
        CommentModel.DeleteCmtByArtId(article_id);
    }

    @Override
    public void DeleteCmtByUserId(int user_id) {
        CommentModel.DeleteCmtByUserId(user_id);
    }
}
