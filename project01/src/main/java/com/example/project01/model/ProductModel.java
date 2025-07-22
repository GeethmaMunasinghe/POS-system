package com.example.project01.model;

import com.example.project01.dto.ProductDTO;
import com.example.project01.tm.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ProductModel {
    private static Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma", "root", "1234");
    }

    public static int saveData(ProductDTO productDTO) {
        try {
            String SQL = "INSERT INTO Product VALUES(?,?,?,?)";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, productDTO.getId());
            preparedStatement.setString(2, productDTO.getName());
            preparedStatement.setString(3, productDTO.getAddress());
            preparedStatement.setInt(4, productDTO.getTel());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int updateData(ProductDTO productDTO) {
        try {
            String SQL = "UPDATE Product SET sname=?, address=?, tel=? WHERE sid=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, productDTO.getName());
            preparedStatement.setString(2, productDTO.getAddress());
            preparedStatement.setInt(3, productDTO.getTel());
            preparedStatement.setString(4, productDTO.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int deleteData(String id) {
        try {
            String SQL = "DELETE FROM Product WHERE sid=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static ArrayList<Product> getAllProducts() {
    }
}
