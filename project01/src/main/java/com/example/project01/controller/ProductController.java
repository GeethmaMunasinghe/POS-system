package com.example.project01.controller;

import com.example.project01.model.SupplierModel;
import com.example.project01.tm.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ProductController {

    public TextField pname;

    public TextField description;
    public TextField unitprice;
    public TableView tableView;
    public ComboBox supplierID;

    public void initialize(){
        loadSupplierIds();
    }

    public void searchBtn(ActionEvent actionEvent) {

    }

    public void deleteBtn(ActionEvent actionEvent) {
    }

    public void updateBtn(ActionEvent actionEvent) {
    }

    public void insertBtn(ActionEvent actionEvent) {

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
