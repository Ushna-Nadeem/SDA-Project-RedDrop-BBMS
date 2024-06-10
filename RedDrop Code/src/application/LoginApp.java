package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginApp extends Application {
    private Stage primaryStage;
    private static final String DONOR_CREDENTIALS_FILE = "donor_credentials.txt";
    private static final String BLOODBANK_CREDENTIALS_FILE = "bloodbank_credentials.txt";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Blood Bank Management System");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(160));
        gridPane.setStyle("-fx-background-color: beige;");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40);
        gridPane.getColumnConstraints().addAll(col1, col2);

        Text title = new Text("RedDrop");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTranslateY(-80); // Adjust the vertical position
        Text title1 = new Text("\"Connecting Heroes, Saving Lives\"");
        title1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title1.setTranslateY(-20); // Adjust the vertical position

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button customerLoginButton = new Button("Login as Donor");
        Button bloodBankLoginButton = new Button("Login as Blood Bank Staff");

        // Apply button styling
        String buttonStyle = "-fx-font-family: 'Verdana'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #8B0000;";
        customerLoginButton.setStyle(buttonStyle);
        bloodBankLoginButton.setStyle(buttonStyle);

        // Add event handlers for the login buttons
        customerLoginButton.setOnAction(e -> validateCredentials(usernameField.getText(), passwordField.getText(), DONOR_CREDENTIALS_FILE));
        bloodBankLoginButton.setOnAction(e -> validateCredentials(usernameField.getText(), passwordField.getText(), BLOODBANK_CREDENTIALS_FILE));

        VBox titleVBox = new VBox(title);
        titleVBox.setAlignment(Pos.CENTER);

        VBox title1VBox = new VBox(title1);
        title1VBox.setAlignment(Pos.CENTER);

        VBox leftVBox = new VBox(usernameLabel, passwordLabel);
        leftVBox.setAlignment(Pos.BASELINE_RIGHT);
        leftVBox.setSpacing(25);
        VBox.setMargin(leftVBox, new Insets(0, 10, 0, 0));

        VBox rightVBox = new VBox(usernameField, passwordField, customerLoginButton, bloodBankLoginButton);
        rightVBox.setAlignment(Pos.CENTER_LEFT);
        rightVBox.setSpacing(15);

        gridPane.add(titleVBox, 0, 0, 2, 1);
        gridPane.add(title1VBox, 0, 0, 2, 1);
        gridPane.add(leftVBox, 0, 1);
        gridPane.add(rightVBox, 1, 1);

        VBox vbox = new VBox(gridPane);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: maroon;");

        Scene scene = new Scene(vbox, 1380, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void validateCredentials(String username, String password, String credentialsFile) {
        boolean validCredentials = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    validCredentials = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (validCredentials) {
            if (credentialsFile.equals(DONOR_CREDENTIALS_FILE)) {
                showCustomerInterface();
            } else if (credentialsFile.equals(BLOODBANK_CREDENTIALS_FILE)) {
                showBloodBankInterface();
            }
        } else {
            displayInvalidCredentialsMessage();
        }
    }

    private void showCustomerInterface() {
        // Create the customer interface scene
        primaryStage.setTitle("RedDrop -- Donor Homepage :)");

        CustomerInterface customerInterface = new CustomerInterface(primaryStage);
        customerInterface.show();
    }

    private void showBloodBankInterface() {
        // Create the blood bank interface scene
        primaryStage.setTitle("RedDrop -- Blood Bank Staff Homepage :)");

        BloodBankInterface bloodBankInterface = new BloodBankInterface(primaryStage);
        bloodBankInterface.show();
    }

    private void displayInvalidCredentialsMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password. Please try again.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}