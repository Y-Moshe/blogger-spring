package com.blogger.bloggerspring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blogger.bloggerspring.Entities.Blog;
import com.blogger.bloggerspring.Interfaces.IBlogService;
import com.blogger.bloggerspring.Repositories.BlogRepository;

@Service
public class BlogService implements IBlogService {
    private BlogRepository _repository;

    public BlogService(BlogRepository repository) {
        _repository = repository;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return (List<Blog>) _repository.findAll();
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return _repository.findById(id);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return _repository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        _repository.deleteById(id);
    }
}
