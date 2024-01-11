package com.example.backendschool_jpa_02.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ArticlesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String password;

    @ManyToOne
    private BoardsEntity boardsEntity;

    @OneToMany(mappedBy = "articlesEntityAndCommentEntity")
    private List<CommentsEntity> commentsEntityList;
}
