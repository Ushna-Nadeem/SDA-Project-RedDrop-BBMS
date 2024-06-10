package application;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MatchedSearchesInterface {
    private static final Random RANDOM = new Random();
    private Stage primaryStage;

    public MatchedSearchesInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Create UI components for cross inventory search

        Label titleLabel = new Label("Cross Inventory Search");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: beige;");
        titleLabel.setTranslateY(-80); // Adjust the vertical position

        Line lineAbove = new Line();
        lineAbove.setEndX(300);
        lineAbove.setTranslateY(-80); // Adjust the vertical position
        lineAbove.setStroke(Color.MAROON);

        Line lineBelow = new Line();
        lineBelow.setEndX(300);
        lineBelow.setTranslateY(-80); // Adjust the vertical position
        lineBelow.setStroke(Color.BEIGE);

        Label bloodTypeLabel = new Label("Enter blood type to search:");
        bloodTypeLabel.setTextFill(Color.	BLACK);
        bloodTypeLabel.setStyle("-fx-font-weight: bold;");

        TextField bloodTypeTextField = new TextField();

        Label amountLabel = new Label("Enter amount:");
        amountLabel.setTextFill(Color.	BLACK);
        amountLabel.setStyle("-fx-font-weight: bold;");

        TextField amountTextField = new TextField();

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #8B0000;");

        // Set event handler for the search button
        searchButton.setOnAction(e -> {
            String bloodType = bloodTypeTextField.getText();
            String amount = amountTextField.getText();

            // Perform search logic and generate result
            String result = performCrossInventorySearch(bloodType, amount);
            displaySearchResult(result, primaryStage);
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(25));
        gridPane.setBackground(createBackground());
        gridPane.add(lineAbove, 0, 0, 2, 1);
        gridPane.add(titleLabel, 0, 1, 2, 1);
        gridPane.add(lineBelow, 0, 2, 2, 1);
        gridPane.add(bloodTypeLabel, 0, 3);
        gridPane.add(bloodTypeTextField, 1, 3);
        gridPane.add(amountLabel, 0, 4);
        gridPane.add(amountTextField, 1, 4);
        gridPane.add(searchButton, 1, 5);

        Scene scene = new Scene(gridPane, 1380, 700);
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Cross-Inventory");
        scene.setFill(createBackground().getFills().get(0).getFill());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String performCrossInventorySearch(String bloodType, String amount) {
        // Perform the cross inventory search logic
        // Generate random result (Found or Not Found)
        // If found, generate random inventory name

        boolean found = RANDOM.nextBoolean();
        String inventoryName = "N/A";
        if (found) {
            String[] inventories = {"Inventory A", "Inventory B", "Inventory C"};
            inventoryName = inventories[RANDOM.nextInt(inventories.length)];
        }

        String result = found ? "Found in " + inventoryName : "Not Found";
        return result;
    }

    private void displaySearchResult(String result, Stage primaryStage) {
        // Display the search result
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Result");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
        showBloodBankInterface(primaryStage);
    }

    private void showBloodBankInterface(Stage primaryStage) {
        BloodBankInterface bloodBankInterface = new BloodBankInterface(primaryStage);
        bloodBankInterface.show();
    }

    private Background createBackground() {
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.MAROON),
                new Stop(1, Color.BEIGE)
        );

        BackgroundFill backgroundFill = new BackgroundFill(backgroundGradient, null, null);
        return new Background(backgroundFill);
    }
}
