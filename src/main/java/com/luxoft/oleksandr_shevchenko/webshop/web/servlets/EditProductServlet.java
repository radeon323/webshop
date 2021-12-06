package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class EditProductServlet extends HttpServlet {
    private ProductService productService;

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("edit_product.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        Product product = getProductParameters(req);
        productService.edit(product);
        resp.sendRedirect(req.getContextPath() + "/products/edit");

        List<Product> products = productService.findAll();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);
        String page = instance.getPage("products_list.html", parameters);
        resp.getWriter().write(page);
    }

    private Product getProductParameters(HttpServletRequest req){
        Product product = Product.builder().
                id(Integer.parseInt(req.getParameter("id")))
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .creationDate(LocalDateTime.parse(req.getParameter("creation_date")))
                .build();
        return product;
    }

}
