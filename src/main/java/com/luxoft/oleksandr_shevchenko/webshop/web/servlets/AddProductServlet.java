package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class AddProductServlet extends HttpServlet {
    private ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("add_product.html");
        resp.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("add_product.html");
        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));

            if(name != null && name.length() > 0 && req.getParameter("price") != null && price > 0) {
                try {
                    Product product = getProductFromRequest(req);
                    productService.add(product);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(" product: " + name + " price " + price);

                resp.getWriter().write(page);
                resp.getWriter().write("<p>Product " + name + " was successfully added</p>");
            } else if (price <= 0) {
                resp.getWriter().write(page);
                resp.getWriter().write("<p>Price value must be positive</p>");
            } else {
                resp.getWriter().write(page);
                resp.getWriter().write("<p>Please fill up all fields!</p>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().write(page);
            resp.getWriter().write("<p>Please fill up all fields!</p>");
        }
    }

    private Product getProductFromRequest(HttpServletRequest req){
        return Product.builder()
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .creationDate(LocalDateTime.now())
                .build();
    }


}





