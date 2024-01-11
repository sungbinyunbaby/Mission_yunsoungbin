package com.example.backendschool_jpa_02.repo;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.BoardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardsRepository extends JpaRepository<BoardsEntity, Long> {
    BoardsEntity findTopByIdOrderByArticlesEntityListIdDesc(Long id);
}

