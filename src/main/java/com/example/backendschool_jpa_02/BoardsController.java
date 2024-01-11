package com.example.backendschool_jpa_02;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.BoardsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardsController {
    private final BoardsService boardsService;
    private final ArticlesService articlesService;
    private final CommentsService commentsService;

    //home, index화면-게시판 종류를 나열한다.
    @GetMapping()
    public String boards(Model model) {
        model.addAttribute("boards", boardsService.findAll());
        return "/home";
    }

    //게시판 내의 모든 게시글 제목을 가져온다, 내림차순
    @GetMapping("/{id}")
    public String board(@PathVariable("id")Long id,
                        Model model)
    {
        //내림차순을 위함. BoardsEntity.articlesEntityList 사용.
        List<ArticlesEntity> articlesForBoardInDescOrder = boardsService.getArticlesForBoardInDescOrder(id);
        model.addAttribute("h1", boardsService.findById(id));
        model.addAttribute("titleDesc", articlesForBoardInDescOrder);
        return "ArticlesHtml/showTitle";
    }

    //게시글 Create
    @GetMapping("/articleCreate")
    public String articleCreate(Model model) {
        List<BoardsEntity> boardList = boardsService.findAll();
        model.addAttribute("boardList", boardList);
        return "ArticlesHtml/articleCreate";
    }

    @PostMapping("/article")
    public String article(@RequestParam("title")String title,
                          @RequestParam("content")String content,
                          @RequestParam("password")String password,
                          @RequestParam("boardsEntity")Long boardsEntity)
    {
        articlesService.save(title, content, password, boardsEntity);
        return "redirect:/boards";
    }




}
