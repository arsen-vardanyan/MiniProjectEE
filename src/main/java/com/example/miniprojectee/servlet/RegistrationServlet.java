package com.example.miniprojectee.servlet;

import com.example.miniprojectee.enums.Gender;
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

@WebServlet(name = "register", value = "/register", loadOnStartup = 1)
public class RegistrationServlet extends HttpServlet {

    private UserManager userManager;

    @Override
    public void init() throws ServletException {
        userManager = new UserManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("reg_name");
        String surname = req.getParameter("reg_surname");
        int age = Integer.parseInt(req.getParameter("reg_age"));
        String email = req.getParameter("reg_email");
        String password = req.getParameter("reg_password");
        Gender gender = Gender.valueOf(req.getParameter("reg_gender"));
        String phone = req.getParameter("reg_phone_number");

        boolean exist = userManager.existByEmail(email);
        if (exist) {
            resp.sendRedirect("/MiniProjectEE_war_exploded/home?reg_error=Email already used");
        } else {
            User currentUser = userManager.save(User.builder()
                    .name(name)
                    .surname(surname)
                    .phoneNumber(phone)
                    .email(email)
                    .password(Md5Util.toHash(password))
                    .age(age)
                    .gender(gender)
                    .build());
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", currentUser);
            resp.sendRedirect("/MiniProjectEE_war_exploded/user-home");

        }

    }
}
