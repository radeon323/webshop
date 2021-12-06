package com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc;

import com.luxoft.oleksandr_shevchenko.webshop.entity.Product;
import static com.luxoft.oleksandr_shevchenko.webshop.entity.Product.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        int id  = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double price  = resultSet.getDouble("price");
        LocalDateTime creationDate = LocalDateTime.now();
        Product product = builder().
                id(id)
                .name(name)
                .price(price)
                .creationDate(creationDate)
                .build();

    return product;
    }
}
