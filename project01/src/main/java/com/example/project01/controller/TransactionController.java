package com.example.project01.controller;
import com.example.project01.dto.TransactionDTO;
import com.example.project01.model.TransactionModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionController {
    @FXML private ComboBox<String> cmbProductIDs;
    @FXML private TextField txtName;
    @FXML private TextField txtDescription;
    @FXML private TextField txtUnitPrice;
    @FXML private Label lblQtyOnHand;
    @FXML private TextField txtQty;
    @FXML private Button btnAddToCart;
    @FXML private TableView<TransactionDTO> tblItems;
    @FXML private TableColumn<TransactionDTO, String> colItemCode;
    @FXML private TableColumn<TransactionDTO, String> colDescription;
    @FXML private TableColumn<TransactionDTO, Integer> colQty;
    @FXML private TableColumn<TransactionDTO, Double> colUnitPrice;
    @FXML private TableColumn<TransactionDTO, Double> colTotal;
    @FXML private Label lblNetTotal;

    private final TransactionModel itemModel = new TransactionModel();
    private final ObservableList<TransactionDTO> cartList = FXCollections.observableArrayList();

    public void initialize() {
        for (TransactionDTO item : itemModel.getAllItems()) {
            cmbProductIDs.getItems().add(item.getItemCode());
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand")); // Reused here for QTY Customer
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(cellData -> {
            double total = cellData.getValue().getQtyOnHand() * cellData.getValue().getUnitPrice();
            return new ReadOnlyObjectWrapper<>(total);
        });

        tblItems.setItems(cartList);
    }

    @FXML
    private void onProductSelected() {
        String selectedId = cmbProductIDs.getValue();
        if (selectedId != null) {
            TransactionDTO item = itemModel.findItemById(selectedId);
            if (item != null) {
                txtName.setText(item.getItemCode());
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            }
        }
    }

    @FXML
    private void onAddToCart() {
        String id = cmbProductIDs.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        TransactionDTO selectedItem = itemModel.findItemById(id);

        if (selectedItem != null && qty <= selectedItem.getQtyOnHand()) {
            cartList.add(new TransactionDTO(
                    selectedItem.getItemCode(),
                    selectedItem.getDescription(),
                    qty,
                    selectedItem.getUnitPrice()
            ));

            calculateNetTotal();
        }
    }

    private void calculateNetTotal() {
        double total = 0;
        for (TransactionDTO item : cartList) {
            total += item.getQtyOnHand() * item.getUnitPrice();
        }
        lblNetTotal.setText(String.valueOf(total));
    }


}
