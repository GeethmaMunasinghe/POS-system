package com.example.project01;


import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SupplierController {

    public TextField txtTel;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtId;
    public TableView tableView;

    public void addBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());

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

    public void updateBtn(ActionEvent actionEvent) {

    }

    public void deleteBtn(ActionEvent actionEvent) {

    }

    public void searchBtn(ActionEvent actionEvent) {
    }
}