package com.blogger.bloggerspring.Services;

import java.util.List;
import java.util.Optional;

import com.blogger.bloggerspring.Entities.Blog;

public interface BlogService {
    public List<Blog> getAllBlogs();

    public Optional<Blog> getBlogById(Long id);

    public Blog saveBlog(Blog blog);

    public void deleteBlog(Long id);
}
