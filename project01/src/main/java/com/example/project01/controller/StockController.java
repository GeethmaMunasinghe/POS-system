package com.example.project01.controller;

import com.example.project01.dto.StockDTO;
import com.example.project01.model.StockModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class StockController {
    @FXML
    private ComboBox<String> cmbProductId;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TableView<StockDTO> tblStock;
    @FXML
    private TableColumn<StockDTO, Integer> colId;
    @FXML
    private TableColumn<StockDTO, String> colProductId;
    @FXML
    private TableColumn<StockDTO, Integer> colQuantity;
    @FXML
    private TableColumn<StockDTO, LocalDateTime> colUpdateTime;

    @FXML
    public void initialize() {
        // Set up table columns
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colProductId.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getProductId()));
        colQuantity.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
        colUpdateTime.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getUpdateTime()));

        loadTableData();
        loadProductIds(); // Load product IDs into ComboBox
    }

    private void loadProductIds() {
        // Ideally, fetch from ProductModel
        cmbProductId.setItems(FXCollections.observableArrayList("P001", "P002", "P003")); // Replace with actual product IDs
    }

    private void loadTableData() {
        ObservableList<StockDTO> list = FXCollections.observableArrayList(StockModel.getAllStock());
        tblStock.setItems(list);
    }

    @FXML
    private void insertStock() {
        StockDTO stock = StockDTO.builder()
                .productId(cmbProductId.getValue())
                .quantity(Integer.parseInt(txtQuantity.getText()))
                .updateTime(LocalDateTime.now())
                .build();

        StockModel.insertStock(stock);
        loadTableData();
    }

    @FXML
    private void updateStock() {
        StockDTO selected = tblStock.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setProductId(cmbProductId.getValue());
            selected.setQuantity(Integer.parseInt(txtQuantity.getText()));
            selected.setUpdateTime(LocalDateTime.now());

            StockModel.updateStock(selected);
            loadTableData();
        }
    }

    @FXML
    private void deleteStock() {
        StockDTO selected = tblStock.getSelectionModel().getSelectedItem();
        if (selected != null) {
            StockModel.deleteStock(selected.getId());
            loadTableData();
        }
    }

    @FXML
    private void searchStock() {
        // Optional: Implement search logic (e.g., by ID or productId)
    }
}

