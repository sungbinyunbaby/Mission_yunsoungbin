package com.example.backendschool_jpa_02.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class BoardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boards;

    @OneToMany(mappedBy = "boardsEntity")
    private List<ArticlesEntity> articlesEntityList;

}
