package com.example.backendschool_jpa_02;

import com.example.backendschool_jpa_02.entity.ArticlesEntity;
import com.example.backendschool_jpa_02.entity.BoardsEntity;
import com.example.backendschool_jpa_02.entity.CommentsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticlesController {
    private final BoardsService boardsService;
    private final ArticlesService articlesService;
    private final CommentsService commentsService;
    //게시글 하나 단일조회 화면
    //주의 * 게시판의 id가 오는게 아니라 article의 id가 온다.
    //boards id=1의 article id=3을 출력
    @GetMapping("/{id}/article")
    public String content(@PathVariable("id")Long id,
                          Model model)
    {
        //게시판 이름을 그대로 가져오기 위해 id랑 맞는 articlesEntity.getBoardsEntity 사용.
        model.addAttribute("h1Board", articlesService.findBoardsByArticleId(id));
        //articleEntity id에 맞는 title, content 출력.
        model.addAttribute("article", articlesService.findById(id));
        //댓글
        model.addAttribute("commentList", articlesService.findById(id));
        return "/ArticlesHtml/showArticle";
    }

    //댓글 CRUD
    //댓글 Create
    @PostMapping("/{id}/comment")
    public String comment(@PathVariable("id")Long id,
                          @RequestParam("comment")String comment,
                          @RequestParam("password")String password)
    {
        commentsService.save(id, comment, password);
        //이동없이 showArticle 화면 그대로.
        return String.format("redirect:/article/{id}/article", id);
    }

    //댓글 Delete 전 비밀번호 확인
    @GetMapping("/{articleId}/commentPassword/{deleteId}/deletePassword")
    public String checkCommentDelete(@PathVariable("articleId")Long articleId,
                                @PathVariable("deleteId")Long deleteId,
                                Model model)
    {
         //다음 페이지에 필요한 데이터를 계속 가져가기 위함
        model.addAttribute("delete", commentsService.findById(deleteId));
        model.addAttribute("comment", articlesService.findById(articleId));
        return "CommentsHtml/checkDeletePassword";

    }

    //return password.equals? 삭제완료 : 비밀번호입력창
    @PostMapping("/{articleId}/comment/{deleteId}/delete")
    public String delete(@PathVariable("articleId")Long articleId,
                         @PathVariable("deleteId")Long deleteId,
                         @RequestParam("password")String password,
                         Model model)//틀렸을 시 다시 비밀번호 페이지 사용하기 위함.
    {
        //삭제할 댓글 아이디로 비밀번호 알아내서 비교하기.
        CommentsEntity delete = commentsService.findById(deleteId);
        //일치하면 게시글 단일 조회화면으로 이동.
        if (password.equals(delete.getPassword())){
            commentsService.deleteId(deleteId);
            return String.format("redirect:/article/{articleId}/article",articleId);
            //일치하지 않으면 비밀번호 요청 반복.
        }else {
            //돌아갈 페이지에 필요한 데이터
            model.addAttribute("delete", commentsService.findById(deleteId));
            model.addAttribute("comment", articlesService.findById(articleId));
            return "CommentsHtml/checkDeletePassword";
        }
    }

    //게시글 Update 전 비밀번호 확인
    @GetMapping("/{id}/updatePassword")
    public String updatePassword(@PathVariable("id")Long id,
                                Model model){
        //다음 페이지에 사용할 데이터를 계속 가져가기 위함.
        model.addAttribute("articleUpdate", articlesService.findById(id));
        return "ArticlesHtml/checkArticlePassword";
    }

    @PostMapping("/{id}/articlePassword")
    public String articleUpdatePassword(@PathVariable("id")Long id,
                                        @RequestParam("password")String password,
                                        Model model){
        //돌아갈 페이지에 필요한 테이터
        model.addAttribute("articleUpdate", articlesService.findById(id));
        //비밀번호 확인
        ArticlesEntity updatePassword = articlesService.findById(id);
        if (password.equals(updatePassword.getPassword())){
            //게시판 종류 수정 시 필요한 데이터
            List<BoardsEntity> boardList = boardsService.findAll();
            model.addAttribute("bordList", boardList);
            //맞으면 수정페이지로 이동
            return "/ArticlesHtml/articleUpdate";
        }else {//비밀번호 틀리면 계속 비밀번호 요청.
            return "ArticlesHtml/checkArticlePassword";
        }
    }

    @PostMapping("{id}/update")
    public String articleUpdate(@PathVariable("id")Long id,
                                @RequestParam("title")String title,
                                @RequestParam("content")String content,
                                @RequestParam("password")String password,
                                @RequestParam("boardsEntity")Long boardsEntity)
    {
        articlesService.update(id, title, content, password, boardsEntity);
        //수정한 게시판으로 타이틀 모음 페이지로 이동
        return String.format("redirect:/boards/%s", boardsEntity);
    }

    //게시글 Delete 전 비밀번호 확인
    @GetMapping("/{id}/deletePassword")
    public String deletePassword(@PathVariable("id")Long id,
                                 Model model){
        model.addAttribute("deletePassword", articlesService.findById(id));
        return "ArticlesHtml/checkDeletePassword";
    }

    //비밀번호 맞으면 삭제, 틀리면 계속 비밀번호 요청
    @PostMapping("/{id}/deleteArticlePassword")
    public String deleteArticlePassword(@PathVariable("id")Long id,
                                        @RequestParam("password")String password,
                                        Model model)
    {
        ArticlesEntity deleteArticlePassword = articlesService.findById(id);
        model.addAttribute("deletePassword", deleteArticlePassword);
        if (password.equals(deleteArticlePassword.getPassword())){
            articlesService.deleteById(id);
            return "redirect:/boards";
        }else {
            return "ArticlesHtml/checkDeletePassword";
        }
    }



}
