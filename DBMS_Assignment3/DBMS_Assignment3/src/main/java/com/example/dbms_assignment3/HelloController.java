package com.example.dbms_assignment3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class HelloController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    //this function to navigate after login
    public void openHome(MouseEvent event) throws IOException {
        try {
            DBConnection con = new DBConnection();
            //check username password validation here
            String username = usernameField.getText();
            String password = passwordField.getText();
            String q = "select * from user where Username = '" + username + "' and  Password = '" + password + "'";
            ResultSet res = con.statement.executeQuery(q);
            if (res.next()) {
                showAlert("Login Successful", "Welcome, " + username + "!");
                root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}