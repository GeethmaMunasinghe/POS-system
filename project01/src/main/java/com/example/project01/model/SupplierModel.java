package com.example.project01.model;

import com.example.project01.dto.SupplierDTO;
import com.example.project01.tm.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierModel {

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma", "root", "1234");
    }

    public static int saveData(SupplierDTO supplierDTO) {
        try {
            String SQL = "INSERT INTO Suppliers VALUES(?,?,?,?)";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, supplierDTO.getId());
            preparedStatement.setString(2, supplierDTO.getName());
            preparedStatement.setString(3, supplierDTO.getAddress());
            preparedStatement.setInt(4, supplierDTO.getTel());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int updateData(SupplierDTO supplierDTO) {
        try {
            String SQL = "UPDATE Suppliers SET sname=?, address=?, tel=? WHERE sid=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, supplierDTO.getName());
            preparedStatement.setString(2, supplierDTO.getAddress());
            preparedStatement.setInt(3, supplierDTO.getTel());
            preparedStatement.setString(4, supplierDTO.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int deleteData(String id) {
        try {
            String SQL = "DELETE FROM Suppliers WHERE sid=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static ResultSet searchData(String id) {
        try {
            String SQL = "SELECT * FROM Suppliers WHERE sid=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Supplier> getAllSuppliers() {
        try {
            String SQL = "SELECT * FROM Suppliers";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet result = preparedStatement.executeQuery();

            ArrayList<Supplier> suppliers = new ArrayList<>();
            while (result.next()) {
                suppliers.add(new Supplier(
                        result.getString("sid"),
                        result.getString("sname"),
                        result.getString("address"),
                        result.getInt("tel"))
                );
            }
            return suppliers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
