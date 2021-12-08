package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;


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

                String msgSuccess = "Product <i>" + name + "</i> was successfully added!";
                Map<String, Object> parameters = Map.of("msgSuccess", msgSuccess);
                String page = instance.getPage("add_product.html", parameters);
                resp.getWriter().write(page);

            } else if (price <= 0) {
                String errorMsgPos = "Price value must be positive";
                Map<String, Object> parameters = Map.of("errorMsgPos", errorMsgPos);
                String pageError = instance.getPage("add_product.html", parameters);
                resp.getWriter().write(pageError);
            } else {
                String errorMsgFill = "Please fill up all fields!";
                Map<String, Object> parameters = Map.of("errorMsgFill", errorMsgFill);
                String pageError = instance.getPage("add_product.html", parameters);
                resp.getWriter().write(pageError);
            }
        } catch (Exception e) {
            String errorMsgFill = "Please fill up all fields!";
            Map<String, Object> parameters = Map.of("errorMsgFill", errorMsgFill);
            String pageError = instance.getPage("add_product.html", parameters);
            resp.getWriter().write(pageError);
        }
    }

    private Product getProductFromRequest(HttpServletRequest req){
        return Product.builder()
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .creationDate(Timestamp.valueOf(LocalDateTime.now().withNano(0).withSecond(0)))
                .build();
    }


}





