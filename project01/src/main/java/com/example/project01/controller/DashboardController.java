package com.example.project01.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.events.MouseEvent;

import java.util.Optional;

public class DashboardController {
    public BorderPane bp;
    public AnchorPane ap;

    public void transaction(MouseEvent mouseEvent){loadPage ("transaction");}
    public void product(MouseEvent mouseEvent){loadPage("product");}
    public void supplier(MouseEvent mouseEvent){loadPage("supplier");}
    public void stock(MouseEvent mouseEvent){ loadPage("stock");}
    public void reports(MouseEvent mouseEvent){loadPage("report");}

    public void loadPage(String page){
        Parent root=null;
        try{
            root= FXMLLoader.load(getClass().getResource("com/example/project01"+page+".fxml"));

        }
        catch (Exception e){
            System.out.println(e);
        }
        bp.setCenter(root);
    }

    public void logout(ActionEvent actionEvent){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> result=alert.showAndWait();

    }
}
