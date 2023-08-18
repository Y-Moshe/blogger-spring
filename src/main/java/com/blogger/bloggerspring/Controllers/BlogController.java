package com.blogger.bloggerspring.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.bloggerspring.Entities.Blog;
import com.blogger.bloggerspring.Errors.ApiError;
import com.blogger.bloggerspring.Services.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Blog Controller")
public class BlogController {
    private BlogService _blogService;

    public BlogController(BlogService blogService) {
        _blogService = blogService;
    }

    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> GetAllBlogs() {
        return new ResponseEntity<>(_blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> GetSingleBlog(@PathVariable Long id) {
        Optional<Blog> blog = _blogService.getBlogById(id);
        if (blog.isPresent())
            return new ResponseEntity<>(blog.get(), HttpStatus.OK);
        else
            throw new ApiError("Blog was not found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/blog")
    public ResponseEntity<Blog> CreateBlog(@RequestBody @Valid Blog blog) {
        return new ResponseEntity<>(_blogService.saveBlog(blog), HttpStatus.CREATED);
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<Blog> UpdateBlog(@PathVariable Long id, @RequestBody @Valid Blog blog) {
        blog.setId(id);
        return new ResponseEntity<>(_blogService.saveBlog(blog), HttpStatus.OK);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<HttpStatus> DeleteBlog(@PathVariable Long id) {
        _blogService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}