package com.blogger.bloggerspring.Services;

import java.util.List;
import java.util.Optional;

import com.blogger.bloggerspring.Entities.Comment;

public interface CommentService {
    public List<Comment> getAllComments();

    public Optional<Comment> getCommentById(Long id);

    public Comment saveComment(Comment comment);

    public void deleteComment(Long id);
}
