package com.example.backendschool_jpa_02.dto;

import lombok.Data;

@Data
public class ArticlesDto {
    private Long id;
    private String title;
    private String content;
    private String password;
}
