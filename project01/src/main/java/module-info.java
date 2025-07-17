module com.example.project01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.project01 to javafx.fxml;
    exports com.example.project01;
}