package com.example.backendschool_jpa_02.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private String password;

    @ManyToOne
    private ArticlesEntity articlesEntityAndCommentEntity;
}
