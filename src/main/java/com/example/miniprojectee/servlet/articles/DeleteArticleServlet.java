package com.example.miniprojectee.servlet.articles;

import com.example.miniprojectee.manager.ArticleManager;
import com.example.miniprojectee.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete-article", value = "/delete-article", loadOnStartup = 1)
public class DeleteArticleServlet extends HttpServlet {

    private ArticleManager articleManager;

    @Override
    public void init() throws ServletException {
        articleManager = new ArticleManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        articleManager.deleteArticle(Integer.parseInt(req.getParameter("deletedArticleId")), (User) req.getSession().getAttribute("currentUser"));
        resp.sendRedirect("/MiniProjectEE_war_exploded/user-home");
    }
}
