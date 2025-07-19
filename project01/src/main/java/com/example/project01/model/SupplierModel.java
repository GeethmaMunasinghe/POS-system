package com.example.project01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SupplierModel {
    public void saveData(){
        try {
            //01. create a SQL
            String SQL= "INSERT INTO Supplier VALUES(?,?,?,?)";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,address);
            preparedStatement.setInt(4,tel);

            //05. Execute the sql
            int result=preparedStatement.executeUpdate();

            if (result>=0){
                System.out.println("Added successfully");
            }else {
                System.out.println("Not added successfully");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
