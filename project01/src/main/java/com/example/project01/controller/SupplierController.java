package com.example.project01.controller;

import com.example.project01.dto.SupplierDTO;
import com.example.project01.model.SupplierModel;
import com.example.project01.tm.Supplier;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierController {

    public TextField txtTel;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtId;
    public TableView<Supplier> tableView;

    public void initialize() {
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tel"));

        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        ArrayList<Supplier> list = SupplierModel.getAllSuppliers();
        tableView.setItems(FXCollections.observableArrayList(list));
    }

    public void addBtn(ActionEvent actionEvent) {
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

    public void updateBtn(ActionEvent actionEvent) {
        Supplier selectedSupplier=tableView.getSelectionModel().getSelectedItem();
        if (selectedSupplier!=null){
            selectedSupplier.setName(txtName.getText());
            selectedSupplier.setTel(Integer.parseInt(txtTel.getText()));
            selectedSupplier.setAddress(txtAddress.getText());
            if (SupplierModel.updateData(selectedSupplier)){
                showAlert("Success","Supplier updated successfully.");
                loadAllSuppliers();
            }else {
                showAlert("Error","Failed to update.");
            }
        }else {
            showAlert("Error","No selected suppliers.");
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {
        Supplier selectedSupplier=tableView.getSelectionModel().getSelectedItem();
        if (selectedSupplier!=null){
            if (SupplierModel.deleteData(selectedSupplier.getId())){
                showAlert("Success", "Supplier Deleted Successfully...");
                loadAllSuppliers();
            }else {
                showAlert("Error","Failed to delete supplier...");
            }
        }else {
            showAlert("Error","No supplier selected...");
        }
    }

    private void showAlert(String title, String message) {

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
}
