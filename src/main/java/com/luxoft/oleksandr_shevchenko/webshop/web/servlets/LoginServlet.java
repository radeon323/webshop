package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
private List<String> userTokens;

    public LoginServlet(List<String> userTokens) {
        this.userTokens = userTokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("login.html");
        resp.getWriter().println(page);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("email - " + email + " : password - " + password);

        String userToken = UUID.randomUUID().toString();
        System.out.println("User Token :" + userToken);

        userTokens.add(userToken);
        Cookie cookie = new Cookie("user-token", userToken);

        resp.addCookie(cookie);
        resp.sendRedirect("/");
    }



}
