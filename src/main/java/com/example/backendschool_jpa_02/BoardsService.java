package com.example.backendschool_jpa_02;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.BoardsEntity;
import com.example.backendschool_jpa_02.repo.ArticlesRepository;
import com.example.backendschool_jpa_02.repo.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final ArticlesRepository articlesRepository;
    //게시판의 종류를 다 가져온다.
    public List<BoardsEntity> findAll() {
        return boardsRepository.findAll();
    }

    public BoardsEntity findById(Long id) {
        return boardsRepository.findById(id).orElse(null);
    }

    public List<ArticlesEntity> getArticlesForBoardInDescOrder(Long id) {
        BoardsEntity boardsEntity = boardsRepository.findById(id).orElse(null);

        if (boardsEntity != null) {
            // 내림차순으로 정렬된 articlesEntityList 반환
            List<ArticlesEntity> articlesEntityList = boardsEntity.getArticlesEntityList();
            articlesEntityList.sort((a1, a2) -> a2.getId().compareTo(a1.getId()));
            return articlesEntityList;
        } else {
            return Collections.emptyList();
        }
    }
}
