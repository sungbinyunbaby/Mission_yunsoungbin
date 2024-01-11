package com.example.backendschool_jpa_02.dto;

import lombok.Data;

@Data
public class CommentsDto {
    private Long id;
    private String comment;
    private String password;
}
