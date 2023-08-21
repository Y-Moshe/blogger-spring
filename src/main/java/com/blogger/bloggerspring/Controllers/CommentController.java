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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.bloggerspring.Entities.Comment;
import com.blogger.bloggerspring.Errors.ApiError;
import com.blogger.bloggerspring.Services.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "Comment Controller")
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService _commentService;

    @GetMapping()
    public ResponseEntity<List<Comment>> GetAllComments() {
        return new ResponseEntity<>(_commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> GetSingleComment(@PathVariable Long id) {
        Optional<Comment> comment = _commentService.getCommentById(id);

        ApiError error = new ApiError("Comment was not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(comment.orElseThrow(() -> error), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> CreateComment(@RequestBody @Valid Comment comment) {
        return new ResponseEntity<>(_commentService.saveComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> UpdateComment(@PathVariable Long id, @RequestBody @Valid Comment comment) {
        comment.setId(id);
        return new ResponseEntity<>(_commentService.saveComment(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> DeleteComment(@PathVariable Long id) {
        _commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
