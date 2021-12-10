package com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


class JdbcProductDaoTest {

    @Test
    public void testFindAllReturnCorrectData() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<Product> products = jdbcProductDao.findAll();
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotEquals(0, product.getPrice());
            assertNotNull(product.getCreationDate());
        }
    }


    @Test
    public void testFindById() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        Product product = jdbcProductDao.prFindById(26);
        assertEquals(123, product.getPrice());
        assertEquals("www", product.getName());
    }


}