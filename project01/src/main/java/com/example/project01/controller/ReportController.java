package com.example.project01.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReportController {
        @FXML
        void goBack(ActionEvent event) {

            CommonMethod.goToBack(event,getClass());

        }

}
