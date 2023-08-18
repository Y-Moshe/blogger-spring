package com.blogger.bloggerspring.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogger.bloggerspring.Entities.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
