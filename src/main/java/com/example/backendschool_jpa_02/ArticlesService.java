package com.example.backendschool_jpa_02;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.BoardsEntity;
import com.example.backendschool_jpa_02.repo.ArticlesRepository;
import com.example.backendschool_jpa_02.repo.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final BoardsRepository boardsRepository;
    public ArticlesEntity findById(Long id) {
        return articlesRepository.findById(id).orElse(null);
    }

    public BoardsEntity findBoardsByArticleId(Long id) {
        ArticlesEntity articlesEntity = articlesRepository.findById(id).orElse(null);
        if (articlesEntity != null){
            return articlesEntity.getBoardsEntity();
        }else {
            return null;
        }
    }

    public void update(Long id, String title, String content, String password, Long boardsEntity) {
        ArticlesEntity articlesEntity = findById(id);
        articlesEntity.setTitle(title);
        articlesEntity.setContent(content);
        articlesEntity.setPassword(password);
        articlesEntity.setBoardsEntity(boardsRepository.findById(boardsEntity).orElse(null));

        articlesRepository.save(articlesEntity);

    }

    public void deleteById(Long id) {
        articlesRepository.deleteById(id);
    }


    public void save(String title, String content, String password, Long boardsEntity) {
        ArticlesEntity articlesEntity = new ArticlesEntity();
        articlesEntity.setTitle(title);
        articlesEntity.setContent(content);
        articlesEntity.setPassword(password);
        articlesEntity.setBoardsEntity(boardsRepository.findById(boardsEntity).orElse(null));

        articlesRepository.save(articlesEntity);
    }

}
