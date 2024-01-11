package com.example.backendschool_jpa_02;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.CommentsEntity;
import com.example.backendschool_jpa_02.repo.ArticlesRepository;
import com.example.backendschool_jpa_02.repo.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ArticlesRepository articlesRepository;
    public CommentsEntity findById(Long id) {
        return commentsRepository.findById(id).orElse(null);
    }

    public void save(Long id, String comment, String password) {
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setComment(comment);
        commentsEntity.setPassword(password);
        commentsEntity.setArticlesEntityAndCommentEntity(
                articlesRepository.findById(id).orElse(null)
        );
        commentsRepository.save(commentsEntity);
    }

    public void deleteId(Long deleteId) {
        commentsRepository.deleteById(deleteId);
    }

    public ArticlesEntity comeBack(Long id) {
        CommentsEntity commentsEntity = commentsRepository.findById(id).orElse(null);
        if (commentsEntity != null){
            return commentsEntity.getArticlesEntityAndCommentEntity();
        }else{
            return null;
        }
    }
}
