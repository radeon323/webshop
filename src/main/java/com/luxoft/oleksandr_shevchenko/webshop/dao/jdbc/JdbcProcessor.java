package com.luxoft.oleksandr_shevchenko.webshop.dao.jdbc;


import com.luxoft.oleksandr_shevchenko.webshop.entity.User;

import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class JdbcProcessor {
    static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    static String jdbcUser = "postgres";
    static String jdbcPass = "postgres";
    static String tableName = "users";

//    private static final String CREATE_TABLE = "CREATE TABLE products (id SERIAL not NULL, name VARCHAR(50), price VARCHAR(50), creation_date VARCHAR(50))";
    private static final String CREATE_TABLE = "CREATE TABLE users (id SERIAL not NULL, email VARCHAR(50), password VARCHAR(50), gender VARCHAR(50), firstName VARCHAR(50), lastName VARCHAR(50), about TEXT, age INT NOT NULL DEFAULT 0)";
    private static final String ADD = "INSERT INTO users (email, password, gender, firstName, lastName, about, age) VALUES (?, ?, ?, ?, ?, ?, ?);";

    protected static Connection connect() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
    }

    public static void clearTable(String tableName) throws SQLException {
        try (Connection connection = connect();) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM " + tableName + ";");
            System.out.println("Table " + tableName + " was successfully cleared...");
        }
    }

    public static void dropTable(String tableName) throws SQLException {
        try (Connection connection = connect();) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE " + tableName + ";");
            System.out.println("Table " + tableName + " was successfully dropped...");
        }
    }

    public static void createTable(String tableName) throws SQLException {
        try (Connection connection = connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);
            preparedStatement.execute();
            System.out.println("Table " + tableName + " was successfully created...");
        }
    }

    public void add(User user) throws SQLException {
        try (Connection connection = connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getAbout());
            preparedStatement.setInt(7, user.getAge());
            preparedStatement.executeUpdate();
        }
    }


//    public static String md5(String text) throws NoSuchAlgorithmException {
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        String sole = "j3qq4m3hk8";
//        String txt = text + sole;
//        byte[] bytes = messageDigest.digest(txt.getBytes());
//        return Hex.encodeHexString(bytes);
//    }

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        //createTable(tableName);
        //System.out.println(md5("12345"));

    }


}
