package com.example.miniprojectee.servlet.articles;

import com.example.miniprojectee.manager.ArticleManager;
import com.example.miniprojectee.models.Article;
import com.example.miniprojectee.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "add-article", value = "/add-article", loadOnStartup = 1)
public class AddArticleServlet extends HttpServlet {

    private ArticleManager articleManager;

    @Override
    public void init() throws ServletException {
        articleManager = new ArticleManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        Article savedArticle = articleManager.save(Article.builder()
                .author(currentUser)
                .userId(currentUser.getId())
                .title(req.getParameter("add_article_title"))
                .content(req.getParameter("add_article_content"))
                .build());
        System.out.println("New Article added: " + savedArticle);
        resp.sendRedirect("/MiniProjectEE_war_exploded/user-home");
    }
}
