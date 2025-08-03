package com.example.project01.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class DashboardController {
    public BorderPane bp;
    public AnchorPane ap;

    public void transaction(MouseEvent mouseEvent){loadPage ("transaction");}
    public void products(MouseEvent mouseEvent){loadPage("products");}
    public void supplier(MouseEvent mouseEvent){loadPage("supplier");}
    public void stocks(MouseEvent mouseEvent){ loadPage("stocks");}
    public void reports(MouseEvent mouseEvent){loadPage("reports");}

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
        if (result.isPresent() && result.get()==ButtonType.OK){
            try {
                //load the login page
                Parent root=FXMLLoader.load(getClass().getResource("com/example/project01/login.fxml"));
                Scene scene=new Scene(root);

                //get current stage from the logout button
                Stage stage=(Stage) bp.getScene().getWindow();

                //set the new scene (login page)
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
