package com.example.project01.controller;

import com.example.project01.dto.SupplierDTO;
import com.example.project01.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class SupplierController {

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtTelNo;

    @FXML
    private TableView<SupplierDTO> tblSuppliers;

    @FXML
    private TableColumn<SupplierDTO, String> colId;

    @FXML
    private TableColumn<SupplierDTO, String> colName;

    @FXML
    private TableColumn<SupplierDTO, String> colAddress;

    @FXML
    private TableColumn<SupplierDTO, String> colTelNo;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId()));
        colName.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        colAddress.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAddress()));
        colTelNo.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelNo()));

        loadTable();
    }

    private void loadTable() {
        ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList(SupplierModel.getAllSuppliers());
        tblSuppliers.setItems(supplierList);
    }

    @FXML
    private void handleAdd() {
        SupplierDTO supplier = SupplierDTO.builder()
                .id(txtSupplierId.getText())
                .name(txtSupplierName.getText())
                .address(txtAddress.getText())
                .telNo(txtTelNo.getText())
                .build();

        SupplierModel.insertSupplier(supplier);
        loadTable();
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        SupplierDTO supplier = SupplierDTO.builder()
                .id(txtSupplierId.getText())
                .name(txtSupplierName.getText())
                .address(txtAddress.getText())
                .telNo(txtTelNo.getText())
                .build();

        SupplierModel.updateSupplier(supplier);
        loadTable();
        clearFields();
    }

    @FXML
    private void handleDelete() {
        String id = txtSupplierId.getText();
        SupplierModel.deleteSupplier(id);
        loadTable();
        clearFields();
    }

    @FXML
    private void handleSearch() {
        String id = txtSupplierId.getText();
        SupplierDTO supplier = SupplierModel.searchSupplier(id);
        if (supplier != null) {
            txtSupplierName.setText(supplier.getName());
            txtAddress.setText(supplier.getAddress());
            txtTelNo.setText(supplier.getTelNo());
        }
    }

    private void clearFields() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtAddress.clear();
        txtTelNo.clear();
    }
}
