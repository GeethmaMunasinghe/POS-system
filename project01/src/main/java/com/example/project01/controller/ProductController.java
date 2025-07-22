package com.example.project01.controller;

import com.example.project01.dto.SupplierDTO;
import com.example.project01.model.ProductModel;
import com.example.project01.model.SupplierModel;
import com.example.project01.tm.Product;
import com.example.project01.tm.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {

    public TextField pname;

    public TextField description;
    public TextField unitprice;
    public TableView tableView;
    public ComboBox supplierID;

    public void initialize(){
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("pname"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        tableView.getColumns().get(4).;

        loadSupplierIds();
    }

    public void loadAllProducts(){
        ArrayList<Product> list= ProductModel.getAllProducts();
        tableView.setItems(FXCollections.observableArrayList(list));
    }

    public void searchBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            ResultSet result = SupplierModel.searchData(id);
            if (result != null && result.next()) {
                txtName.setText(result.getString("sname"));
                txtAddress.setText(result.getString("address"));
                txtTel.setText(String.valueOf(result.getInt("tel")));
            } else {
                System.out.println("No supplier found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Search failed due to SQL error.");
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        int result = SupplierModel.deleteData(id);
        if (result >= 0) {
            System.out.println("Deleted successfully");
            loadAllSuppliers();
        } else {
            System.out.println("Not deleted successfully");
        }
    }

    public void updateBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());

        SupplierDTO supplierDTO = new SupplierDTO(id, name, address, tel);
        int result = SupplierModel.updateData(supplierDTO);

        if (result >= 0) {
            System.out.println("Updated successfully");
            loadAllSuppliers();
        } else {
            System.out.println("Not updated successfully");
        }
    }

    public void insertBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String address = txtAddress.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());

        SupplierDTO supplierDTO = new SupplierDTO(id, name, address, tel);
        int result = SupplierModel.saveData(supplierDTO);

        if (result >= 0) {
            System.out.println("Added successfully");
            loadAllSuppliers();
        } else {
            System.out.println("Not added successfully");
        }
    }

    public void loadSupplierIds(){
        ArrayList<Supplier> suppliers= SupplierModel.getAllSuppliers();

        ObservableList idsList= FXCollections.observableArrayList();

        suppliers.forEach(supplier -> {
            idsList.add(supplier.getId());
        });

        supplierID.setItems(idsList);

    }
}
