module com.example.project01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Required for JDBC
    //requires mysql.connector.java; // MySQL Connector (JAR must be on module path)


    opens com.example.project01 to javafx.fxml;
    exports com.example.project01;
}