package com.example.dbms_assignment3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HomepageController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField totalPersonsField;

    @FXML
    private TextField amountPayableField;

    @FXML
    private TextField usernameBookingField;

    @FXML
    private RadioButton trekkingField;

    @FXML
    private RadioButton canopyField;

    @FXML
    private RadioButton mangroveField;

    @FXML
    private RadioButton creditDebitField;

    @FXML
    private RadioButton cashField;

    @FXML
    private DatePicker paymentDateField;



    public void openBookingPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void openLogOutPage(MouseEvent event) throws IOException {
        showAlert("Sign Out","Successful Logout. Thanks");
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openCancelPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cancel.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void confirmBooking(MouseEvent event) throws IOException {
        String noOfPax = totalPersonsField.getText();
        String payableAmount = amountPayableField.getText();
        String bookingUsername = usernameBookingField.getText();
        String TourPackageType = null;
        if (this.trekkingField.isSelected()) {
            TourPackageType = "Wildlife Trekking";
        } else if (this.canopyField.isSelected()) {
            TourPackageType = "Through Mountains";
        }
        else if (this.mangroveField.isSelected()) {
            TourPackageType = "Mangrove Sight";
        }

        LocalDate paymentDate = paymentDateField.getValue();

        String paymentType = null;
        if (this.creditDebitField.isSelected()) {
            paymentType = "Credit/Debit";
        } else if (this.cashField.isSelected()) {
            paymentType = "Cash";
        }

        try {
            DBConnection con = new DBConnection();
            if (noOfPax.equals("")||payableAmount.equals("")||TourPackageType.equals("")||paymentType.equals("")||paymentDate.equals("")||bookingUsername.equals("")) {
                showAlert("All fields not filled", "please fill all fields for booking!");
            } else {
                String q = "insert into booking(bookingPlace,totalPersons,amountPayable,paymentOption,paymentDate,bookingUsername) values('" + TourPackageType + "', '" + noOfPax + "','" + payableAmount + "','" + paymentType + "','" + paymentDate + "','" + bookingUsername +  "' )";
                con.statement.executeUpdate(q);
                System.out.println("Booking is successful");
                showAlert("Booking Successful", "Successful Booking. Thank you");
                root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
