package com.luxoft.oleksandr_shevchenko.webshop.service;

import com.luxoft.oleksandr_shevchenko.webshop.dao.ProductDao;
import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        List<Product> products = productDao.findAll();
        System.out.println("Obtain products: " + products.size());
        return  products;
    }

    public void add(Product product) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        product.setCreationDate(now);
        productDao.add(product);
        System.out.println("Add product " + product.getName());
    }

    public void remove(int id) {
        productDao.remove(id);
        System.out.println("Remove product with id " + id);
    }

    public void edit(Product product) {
        productDao.edit(product);
    }
}
