module com.example.project01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens com.example.project01 to javafx.fxml;
    exports com.example.project01;
    exports com.example.project01.tm;
    opens com.example.project01.tm to javafx.fxml;
    exports com.example.project01.controller;
    opens com.example.project01.controller to javafx.fxml;
}