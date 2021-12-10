package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.service.SecurityService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class ShowAllProductsServlet extends HttpServlet{
    private final ProductService productService;
    private List<String> userTokens;

    public ShowAllProductsServlet(ProductService productService, List<String> userTokens) {
        this.productService = productService;
        this.userTokens = userTokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(SecurityService.isAuth(req, userTokens)) {
            List<Product> products = productService.findAll();
            PageGenerator instance = PageGenerator.instance();
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("products", products);
            String page = instance.getPage("products_list.html", parameters);
            resp.getWriter().write(page);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(SecurityService.isAuth(req, userTokens)) {
            List<Product> products = productService.findAll();
            PageGenerator instance = PageGenerator.instance();
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("products", products);

            try {
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println(id);
                Product product = productService.prFindById(id);
                String name = product.getName();

                productService.remove(id);

                String msgSuccess = "Product " + name + " was successfully deleted!";
                parameters.put("msgSuccess", msgSuccess);

                String page = instance.getPage("products_list.html", parameters);
                resp.getWriter().write(page);

                //Thread.sleep(1000);
//            resp.sendRedirect("/products");


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect("/login");
        }
    }

}
