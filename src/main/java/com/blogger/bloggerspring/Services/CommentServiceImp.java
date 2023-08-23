package com.blogger.bloggerspring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blogger.bloggerspring.Entities.Blog;
import com.blogger.bloggerspring.Entities.Comment;
import com.blogger.bloggerspring.Repositories.CommentRepository;

@Service
public class CommentServiceImp implements CommentService {
    private CommentRepository _repository;

    public CommentServiceImp(CommentRepository repository) {
        _repository = repository;
    }

    @Override
    public List<Comment> getAllComments() {
        return (List<Comment>) _repository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return _repository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        Blog blog = new Blog();
        blog.setId(comment.getBlogId());
        comment.setBlog(blog);
        return _repository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        _repository.deleteById(id);
    }
}
