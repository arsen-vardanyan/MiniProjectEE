package com.example.miniprojectee.servlet;

import com.example.miniprojectee.enums.Gender;
import com.example.miniprojectee.manager.ArticleManager;
import com.example.miniprojectee.manager.UserManager;
import com.example.miniprojectee.models.User;
import com.example.miniprojectee.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "user-home", value = "/user-home", loadOnStartup = 1)
public class UserHomeServlet extends HttpServlet {

    private UserManager userManager;
    private ArticleManager articleManager;

    @Override
    public void init() throws ServletException {
        userManager = new UserManager();
        articleManager = new ArticleManager();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allArticles", articleManager.all());
        req.getRequestDispatcher("WEB-INF/user-home.jsp").forward(req, resp);
    }
}
