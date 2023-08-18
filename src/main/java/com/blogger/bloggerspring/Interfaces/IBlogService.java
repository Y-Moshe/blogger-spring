package com.blogger.bloggerspring.Interfaces;

import java.util.List;
import java.util.Optional;

import com.blogger.bloggerspring.Entities.Blog;

public interface IBlogService {
    public List<Blog> getAllBlogs();

    public Optional<Blog> getBlogById(Long id);

    public Blog saveBlog(Blog blog);

    public void deleteBlog(Long id);
}
