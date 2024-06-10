package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BloodBankInterface {
    private Stage primaryStage;

    public BloodBankInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        Text welcomeText = new Text("Welcome, Staff Member!");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Button viewInventoryButton = new Button("View Inventory");
        Button enableSearchButton = new Button("Enable Cross-Inventory Search");
        Button viewDonationRecordsButton = new Button("View Donation Records");
        Button logoutButton = new Button("Logout");

        // Set the button styles to use maroon color
        String buttonStyle = "-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #800000;";
        viewInventoryButton.setStyle(buttonStyle);
        enableSearchButton.setStyle(buttonStyle);
        viewDonationRecordsButton.setStyle(buttonStyle);
        logoutButton.setStyle(buttonStyle);

        // Add event handlers for the blood bank interface buttons
        viewInventoryButton.setOnAction(e -> showViewInventoryInterface());
        enableSearchButton.setOnAction(e -> showEnableSearchInterface());
        viewDonationRecordsButton.setOnAction(e -> showViewDonationRecordsInterface());
        logoutButton.setOnAction(e -> showLogOutInterface());

        VBox buttonsVBox = new VBox(40, viewInventoryButton, enableSearchButton, viewDonationRecordsButton, logoutButton);
        buttonsVBox.setAlignment(Pos.CENTER_RIGHT);

        HBox contentHBox = new HBox(20, welcomeText, buttonsVBox);
        contentHBox.setAlignment(Pos.CENTER);

        // Add left margin to the welcomeText
        HBox.setMargin(welcomeText, new Insets(0, 300, 0, 0));

        // Add lines above and below the text and buttons
        Line lineAbove = new Line(0, 0, 1250, 0);
        Line lineBelow = new Line(0, 0, 1250, 0);
        lineAbove.setStroke(Color.MAROON);
        lineBelow.setStroke(Color.BEIGE);

        vbox.getChildren().addAll(lineAbove, contentHBox, lineBelow);

        Scene scene = new Scene(vbox, 1380, 700);

        // Create a linear gradient background
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.BEIGE),
                new Stop(1, Color.MAROON)
        );

        // Create a background fill with the gradient
        BackgroundFill backgroundFill = new BackgroundFill(backgroundGradient, null, null);

        // Set the background fill to the VBox
        vbox.setBackground(new Background(backgroundFill));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showViewInventoryInterface() {
        InventoryInterface inventoryInterface = new InventoryInterface(primaryStage);
        inventoryInterface.show();
    }

    private void showEnableSearchInterface() {
        MatchedSearchesInterface matchedSearchesInterface = new MatchedSearchesInterface(primaryStage);
        matchedSearchesInterface.show();
    }

    private void showViewDonationRecordsInterface() {
        DonationRecordsInterface donationRecordsInterface = new DonationRecordsInterface(primaryStage);
        donationRecordsInterface.show();
    }

    private void showLogOutInterface() {
        LoginApp loginInterface = new LoginApp();
        loginInterface.start(primaryStage);
    }
}