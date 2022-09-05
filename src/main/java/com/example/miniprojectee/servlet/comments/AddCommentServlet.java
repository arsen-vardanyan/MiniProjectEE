package com.example.miniprojectee.servlet.comments;

import com.example.miniprojectee.manager.ArticleManager;
import com.example.miniprojectee.manager.CommentManager;
import com.example.miniprojectee.models.Article;
import com.example.miniprojectee.models.Comment;
import com.example.miniprojectee.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "add-comment", value = "/add-comment", loadOnStartup = 1)
public class AddCommentServlet extends HttpServlet {

    private ArticleManager articleManager;
    private CommentManager commentManager;

    @Override
    public void init() throws ServletException {
        articleManager = new ArticleManager();
        commentManager = new CommentManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commentManager.save(Comment.builder()
                .content(req.getParameter("addedCommentContent"))
                .userId(((User) req.getSession().getAttribute("currentUser")).getId())
                .author((User) req.getSession().getAttribute("currentUser"))
                .build(), Article.builder()
                .id(Integer.parseInt(req.getParameter("addedArticleId"))).
                build());
        resp.sendRedirect("/MiniProjectEE_war_exploded/user-home");
    }
}
