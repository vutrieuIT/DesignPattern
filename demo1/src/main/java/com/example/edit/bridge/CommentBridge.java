package com.example.edit.bridge;

import com.example.edit.beans.Comments;

public interface CommentBridge {
    void addComment(Comments comments);

    void DeleteCmtByArtId(int article_id);

    void DeleteCmtByUserId(int user_id);
}
