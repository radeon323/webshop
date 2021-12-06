package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllProductsServlet extends HttpServlet{
    private final ProductService productService;

    public ShowAllProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = productService.findAll();
        PageGenerator instance = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);
        String page = instance.getPage("products_list.html", parameters);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.remove(id);
            resp.sendRedirect(req.getContextPath() + "/delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
