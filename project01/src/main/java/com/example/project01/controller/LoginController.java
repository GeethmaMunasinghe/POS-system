package com.example.project01.controller;

import com.example.project01.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginController {
    public TextField emailField;
    public PasswordField passwordField;
    public Label statusLabel;

    public void handleLogin(ActionEvent actionEvent){
        String emailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String email=null;
        boolean isValid= Pattern.matches(emailRegex,emailField.getText());

        if (isValid){
            email=emailField.getText();
            String password=passwordField.getText();

            if (UserModel.authenticate(email,password)){
                loadDashboard();
            }else {
                statusLabel.setText("Login Failed...Please Try Again.");
            }
        }else {
            statusLabel.setText("Login Failed...Please Enter Valid Email.");
        }

    }

    private void loadDashboard() {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("com/example/project01/main-dashboard-view.fxml"));
            Scene dashboardScene=new Scene(root);
            Stage primaryStage=(Stage) emailField.getScene().getWindow();
            primaryStage.setScene(dashboardScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
