package com.example.thethingsproject;

import javafx.application.Application;
import javafx.scene.Scene; //To retrieve scene class
import javafx.scene.layout.GridPane; //For my grid layout
import javafx.stage.Stage; //To set the stage lol
import javafx.scene.control.Label; //To retrieve label class
import javafx.scene.control.Button; //To retrieve button class
import javafx.scene.control.TextField; //To retrieve textfield class
import javafx.geometry.Insets; //To retrieve insets class which is used to set padding
import javafx.scene.control.Alert; //To retrieve alert class
import javafx.scene.control.Alert.AlertType; //To retrieve alert type class


import java.io.IOException;

public class Login extends Application {

    private Donor donor;
    private PersistenceHandler persistenceHandler;

    private LoginCallback loginCallback;

    public interface LoginCallback {
        void onLoginSuccess(Donor donor);
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }



    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        persistenceHandler = new PersistenceHandler();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10); //Vertical spacing between grid items
        gridPane.setHgap(10); //Horizontal spacing between grid items

        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField("Please enter your username");
        Label passwordLabel = new Label("Password");
        TextField passwordField = new TextField("Please enter your password");

        gridPane.add(usernameLabel, 0, 0); //Column 0, Row 0

        gridPane.add(usernameField, 1, 0); //Column 1, Row 0

        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 200); //Width, Height
        stage.setScene(scene);
        stage.show();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            findDonorAndValidate(username, password);


        });

    }

    private void displayNotification(String message, AlertType alertType)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void findDonorAndValidate(String username, String password)
    {
           donor = persistenceHandler.getDonor(username);
           if (donor == null)
           {
               displayNotification("No such donor exists", AlertType.ERROR);
           }

           else if (donor.getPassword().equals(password))
           {
               displayNotification("Login successful", AlertType.INFORMATION);
               if (loginCallback != null) {
                   loginCallback.onLoginSuccess(donor);
               }
           }

           else
           {
               displayNotification("Incorrect password", AlertType.ERROR);
           }
    }

    public static void main(String[] args) {
        launch();
    }
}