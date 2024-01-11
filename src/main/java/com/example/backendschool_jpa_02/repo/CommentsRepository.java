package com.example.backendschool_jpa_02.repo;

import com.example.backendschool_jpa_02.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
}
