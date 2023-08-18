package com.blogger.bloggerspring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogger.bloggerspring.Entities.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
