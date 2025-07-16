package com.example.project01;


import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;

public class SupplierController {

    public TextField txtTel;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtId;
    public TableView tableView;

    public void addBtn(ActionEvent actionEvent) {
        //01. create a SQL
        String SQL= "INSERT INTO Supplier VALUES('01','Amal','Colombo',0704056789)";

        //02. Run the driver software
        Class.forName("com.mysql.cj.jdbc.Driver");

        //03. create the connection to the DB
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

        //04. create a statement

        //05. Execute the sql


    }

    public void updateBtn(ActionEvent actionEvent) {
    }

    public void deleteBtn(ActionEvent actionEvent) {
    }

    public void searchBtn(ActionEvent actionEvent) {
    }
}