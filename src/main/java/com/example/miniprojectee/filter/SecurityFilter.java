package com.example.miniprojectee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"user-home", "add-article", "delete-article","add-comment"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object currentUser = ((HttpServletRequest) servletRequest).getSession().getAttribute("currentUser");
        if (currentUser != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            System.out.println("There is no user found");
            ((HttpServletResponse)servletResponse).sendRedirect("/MiniProjectEE_war_exploded/home");
        }

    }

    @Override
    public void destroy() {

    }
}
