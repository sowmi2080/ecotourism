module com.example.dbms_assignment3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires mysql.connector.j;
    requires java.sql;

    opens com.example.dbms_assignment3 to javafx.fxml;
    exports com.example.dbms_assignment3;
}