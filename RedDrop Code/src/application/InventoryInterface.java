package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InventoryInterface {
    private Stage primaryStage;

    public InventoryInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        backButton.setOnAction(e -> showBloodBankInterface(primaryStage));

        // Generate random amounts of blood for different blood groups
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        Random random = new Random();
        Map<String, Integer> inventory = new HashMap<>();

        for (String bloodGroup : bloodGroups) {
            int amount = random.nextInt(10) + 1; // Random amount between 1-10 units
            inventory.put(bloodGroup, amount);
        }

        // Create UI components
        Label bloodGroupHeading = new Label("Group");
        bloodGroupHeading.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        bloodGroupHeading.setTextFill(Color.MAROON);
        Label amountHeading = new Label("Amount");
        amountHeading.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        amountHeading.setTextFill(Color.MAROON);
        Label titleLabel = new Label("Blood Inventory");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: beige;");

        Line lineAboveTitle = new Line(0, 0, 300, 0);
        lineAboveTitle.setStroke(Color.MAROON);
        Line lineBelowTitle = new Line(0, 0, 300, 0);
        lineBelowTitle.setStroke(Color.BEIGE);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Add headings for blood group and amount
        gridPane.add(bloodGroupHeading, 0, 0);
        gridPane.add(amountHeading, 1, 0);

        // Add blood group and amount labels to the grid pane
        int row = 1;
        for (String bloodGroup : inventory.keySet()) {
            Label bloodGroupLabel = new Label(bloodGroup);
            Label amountLabel = new Label(String.valueOf(inventory.get(bloodGroup)));

            gridPane.add(bloodGroupLabel, 0, row);
            gridPane.add(amountLabel, 1, row);
            row++;
        }

        // Create a VBox to hold the title, lines, grid pane, and back button
        VBox vbox = new VBox(15, lineAboveTitle, titleLabel, lineBelowTitle, gridPane, backButton);
        vbox.setAlignment(Pos.CENTER);

        // Create a Rectangle shape as the background with beige and maroon gradient
        Rectangle background = new Rectangle(1380, 700);
        background.setFill(createBackground());

        // Create a StackPane to layer the background and content
        StackPane stackPane = new StackPane(background, vbox);
        stackPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(stackPane, 1380, 700);
        primaryStage.setTitle("RedDrop -- Blood Bank Staff Homepage -- Inventory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showBloodBankInterface(Stage primaryStage) {
        BloodBankInterface bloodbankinterface = new BloodBankInterface(primaryStage);
        bloodbankinterface.show();
    }

    private LinearGradient createBackground() {
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.MAROON),
                new Stop(1, Color.BEIGE)
        );
        return backgroundGradient;
    }
}
