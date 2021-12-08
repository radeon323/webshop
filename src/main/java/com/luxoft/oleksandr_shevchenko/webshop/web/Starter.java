package com.luxoft.oleksandr_shevchenko.webshop.web;

import com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc.JdbcProcessor;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.AddProductServlet;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.EditProductServlet;
import com.luxoft.oleksandr_shevchenko.webshop.web.servlets.ShowAllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {

        JdbcProcessor jdbcProcessor = new JdbcProcessor();

        ProductService productService = new ProductService(jdbcProcessor);

        AddProductServlet addProductServlet = new AddProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);

        ShowAllProductsServlet showAllProductsServlet = new ShowAllProductsServlet(productService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        context.addServlet(new ServletHolder(editProductServlet), "/products/edit");

        context.addServlet(new ServletHolder(showAllProductsServlet), "/products");
        context.addServlet(new ServletHolder(showAllProductsServlet), "/*");

        Server server = new Server(3000);
        server.setHandler(context);

        server.start();
    }
}