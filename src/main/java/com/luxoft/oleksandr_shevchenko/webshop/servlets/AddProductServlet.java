package com.luxoft.oleksandr_shevchenko.webshop.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.jdbc.JdbcProcessor;
import com.luxoft.oleksandr_shevchenko.webshop.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("add_product.html", new HashMap<>());
        response.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            JdbcProcessor jdbcProcessor = new JdbcProcessor();

            if(name != null && name.length() > 0 && req.getParameter("price") != null) {
                try {
                    jdbcProcessor.add(name, price);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(" product: " + name + " price " + price);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("/product/add");
                String msg = "Product " + name + " was successfully added";
                response.getWriter().println(msg);


            } else {
                response.getWriter().println("Please fill up all fields!");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Please fill up all fields!");
        }

    }






}
