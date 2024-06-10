package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DonationRecordsInterface {
    private Stage primaryStage;
    private static final String DONOR_CREDENTIALS_FILE = "donor_credentials.txt";

    public DonationRecordsInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Create the donation record interface scene
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Donation Records");

        Pane pane = new Pane();
        pane.setBackground(createBackground());

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPrefWidth(400);
        vbox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

        Line lineAbove = new Line();
        lineAbove.setEndX(400);
        lineAbove.setStroke(Color.MAROON);
        lineAbove.setTranslateY(-80); // Adjust the vertical position

        Line lineBelow = new Line();
        lineBelow.setEndX(400);
        lineBelow.setStroke(Color.BEIGE);
        lineBelow.setTranslateY(-80); // Adjust the vertical position

        Label titleLabel = new Label("Donation Records");
        titleLabel.setTranslateY(-80); // Adjust the vertical position
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: beige;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #8B0000; -fx-text-fill: white;");
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #8B0000; -fx-text-fill: white;");
        backButton.setOnAction(e -> showBloodBankInterface(primaryStage));

        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setSpacing(15);

        // Add event handler for the search button
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                if (checkDonorExists(name)) {
                    // Generate random result for the entered name
                    String randomId = generateRandomId();
                    String randomBloodGroup = generateRandomBloodGroup();
                    String randomDate = generateRandomDate();
                    String randomAmount = generateRandomAmount();

                    // Display the generated result
                    Label nameLabel = new Label("Name: " + name);
                    Label idLabel = new Label("ID: " + randomId);
                    Label bloodGroupLabel = new Label("Blood Group: " + randomBloodGroup);
                    Label dateLabel = new Label("Date: " + randomDate);
                    Label amountLabel = new Label("Amount: " + randomAmount);

                    resultBox.getChildren().addAll(nameLabel, idLabel, bloodGroupLabel, dateLabel, amountLabel);
                } else {
                    // Display Donor not found message
                    Label notFoundLabel = new Label("Donor not found.");
                    resultBox.getChildren().add(notFoundLabel);
                }

                vbox.getChildren().clear();
                vbox.getChildren().addAll(titleLabel, nameField, searchButton, backButton, resultBox);
            }
        });

        vbox.getChildren().addAll(lineAbove, titleLabel, lineBelow, nameField, searchButton, backButton);

        pane.getChildren().addAll(vbox);
        pane.setMinSize(1380, 700);
        vbox.setLayoutX((1380 - vbox.getPrefWidth()) / 2);
        vbox.setLayoutY((700 - vbox.getHeight()) / 2);

        Scene scene = new Scene(pane, 1380, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean checkDonorExists(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DONOR_CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String generateRandomId() {
        // Generate a random ID
        // Replace this with your own implementation
        return "12345";
    }

    private String generateRandomBloodGroup() {
        // Generate a random blood group
        // Replace this with your own implementation
        return "A+";
    }

    private String generateRandomDate() {
        // Generate a random date
        // Replace this with your own implementation
        return "2023-06-01";
    }

    private String generateRandomAmount() {
        // Generate a random amount
        // Replace this with your own implementation
        return "500 ml";
    }

    private void showBloodBankInterface(Stage primaryStage) {
        BloodBankInterface bloodBankInterface = new BloodBankInterface(primaryStage);
        bloodBankInterface.show();
    }

    private Background createBackground() {
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.BEIGE),
                new Stop(1, Color.MAROON)
        );

        BackgroundFill backgroundFill = new BackgroundFill(backgroundGradient, null, null);
        return new Background(backgroundFill);
    }
}
