package com.luxoft.oleksandr_shevchenko.webshop.web;

import com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc.JdbcProductDao;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.AddProductServlet;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.EditProductServlet;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.LoginServlet;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.ShowAllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {

        JdbcProductDao jdbcProductDao = new JdbcProductDao();

        ProductService productService = new ProductService(jdbcProductDao);

        List<String> userTokens = new ArrayList<>();

        ShowAllProductsServlet showAllProductsServlet = new ShowAllProductsServlet(productService, userTokens);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(showAllProductsServlet), "/products");
        context.addServlet(new ServletHolder(showAllProductsServlet), "/*");

        context.addServlet(new ServletHolder(new AddProductServlet(productService, userTokens)), "/products/add");
        context.addServlet(new ServletHolder(new EditProductServlet(productService, userTokens)), "/products/edit");
        context.addServlet(new ServletHolder(new LoginServlet(userTokens)), "/login");

        Server server = new Server(3000);
        server.setHandler(context);

        server.start();
    }
}