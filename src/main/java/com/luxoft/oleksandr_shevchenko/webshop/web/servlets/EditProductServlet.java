package com.luxoft.oleksandr_shevchenko.webshop.web.servlets;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import com.luxoft.oleksandr_shevchenko.webshop.service.ProductService;
import com.luxoft.oleksandr_shevchenko.webshop.web.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;



public class EditProductServlet extends HttpServlet {
    private ProductService productService;

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("product", product);
        PageGenerator instance = PageGenerator.instance();
        String page = instance.getPage("edit_product.html", parameters);
        resp.getWriter().write(page);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator instance = PageGenerator.instance();
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = getProductFromRequest(req);
            product.setId(id);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("product", product);

            String page = instance.getPage("edit_product.html", parameters);

            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));

            if(name != null && name.length() > 0 && req.getParameter("price") != null && price > 0) {

                productService.edit(product);

                String msgSuccess = "</br></br><p align='center'><strong>Product <i>" + name + "</i> was successfully changed!</strong><p>";
                resp.getWriter().write(page);
                resp.getWriter().println(msgSuccess);

            } else if (price <= 0) {
                String errorMsgPos = "</br></br><p align='center'><strong>Price value must be positive</strong><p>";
                resp.getWriter().write(page);
                resp.getWriter().println(errorMsgPos);
            } else {
                String errorMsgFill = "</br></br><p align='center'><strong>Please fill up all fields</strong><p>";
                resp.getWriter().write(page);
                resp.getWriter().println(errorMsgFill);
            }
        } catch (Exception e) {
//            String page = instance.getPage("edit_product.html");
            String errorMsgPos = "Please fill up all fields";
//            resp.getWriter().write(page);
            resp.getWriter().println(errorMsgPos);
        }

    }

    private Product getProductFromRequest(HttpServletRequest req){
        Product product = Product.builder().
                name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .build();
        return product;
    }
}
