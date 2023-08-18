package com.blogger.bloggerspring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Getter
@Setter
// @RequiredArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable {

    @NotBlank
    private String text;

    @Transient
    private Long blogId;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;
}
