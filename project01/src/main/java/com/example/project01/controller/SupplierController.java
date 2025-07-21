package com.example.project01.controller;


import com.example.project01.dto.SupplierDTO;
import com.example.project01.model.SupplierModel;
import com.example.project01.tm.Supplier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class SupplierController {

    public TextField txtTel;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtId;
    public TableView<Supplier> tableView;

    public void initialize(){
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tel"));

        ArrayList<Supplier> List=getAllSuppliers();

        tableView.setItems(FXCollections.observableArrayList(List));

    }

    public void addBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());

        SupplierDTO supplierDTO = new SupplierDTO(id, name, address, tel);
        int result=SupplierModel.saveData(supplierDTO);

        if (result>=0){
            System.out.println("Added successfully");
            initialize();
        }else {
            System.out.println("Not added successfully");
        }

    }

    public void updateBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());

        try {
            //01. create a SQL
            String SQL= "UPDATE Supplier SET sname=?,address=?,tel=? WHERE sid=?";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setInt(3,tel);
            preparedStatement.setString(4,id);

            //05. Execute the sql
            int result=preparedStatement.executeUpdate();

            if (result>=0){
                System.out.println("Updated successfully");
            }else {
                System.out.println("Not updated successfully");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void deleteBtn(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            //01. create a SQL
            String SQL= "DELETE from Supplier WHERE sid=?";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setString(1,id);

            //05. Execute the sql
            int result=preparedStatement.executeUpdate();

            if (result>=0){
                System.out.println("Deleted successfully");
            }else {
                System.out.println("Not deleted successfully");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void searchBtn(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            //01. create a SQL
            String SQL= "SELECT * from Supplier WHERE sid=?";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setString(1,id);

            //05. Execute the sql
            ResultSet result =preparedStatement.executeQuery();
            if (result.next()){
                txtName.setText(result.getString("sname"));
                txtAddress.setText(result.getString("address"));
                txtTel.setText(String.valueOf(result.getInt("tel")));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Supplier> getAllSuppliers(){

        try {
            //01. create a SQL
            String SQL= "SELECT * from Supplier";

            //02. Run the driver software
            Class.forName("com.mysql.cj.jdbc.Driver");

            //03. create the connection to the DB
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem_geethma","root","1234");

            //04. create a statement
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);

            //05. Execute the sql
            ResultSet result =preparedStatement.executeQuery();

            ArrayList<Supplier> suppliers=new ArrayList<>();
            while (result.next()){

                suppliers.add(new Supplier(result.getString("sid"),
                        result.getString("sname"),
                        result.getString("address"),
                        result.getInt("tel"))
                );
            }

            return suppliers;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}