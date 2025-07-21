package com.example.project01.model;

import com.example.project01.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SupplierModel {
    public static int saveData(SupplierDTO supplierDTO){
        try {
            //01. create a SQL
            String SQL= "INSERT INTO Supplier VALUES(?,?,?,?)";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setString(1,supplierDTO.getId());
            preparedStatement.setString(2,supplierDTO.getName());
            preparedStatement.setString(3,supplierDTO.getAddress());
            preparedStatement.setInt(4, supplierDTO.getTel());

            //05. Execute the sql
            int result=preparedStatement.executeUpdate();

            return result;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
