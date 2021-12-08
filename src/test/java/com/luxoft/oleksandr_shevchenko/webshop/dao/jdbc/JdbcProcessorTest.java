package com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcProcessorTest {

    @Test
    public void testFindAllReturnCorrectData() {
        JdbcProcessor jdbcProcessor = new JdbcProcessor();
        List<Product> products = jdbcProcessor.findAll();
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
        JdbcProcessor jdbcProcessor = new JdbcProcessor();
        Product product = jdbcProcessor.findById(26);
        assertEquals(123, product.getPrice());
        assertEquals("www", product.getName());
    }
}