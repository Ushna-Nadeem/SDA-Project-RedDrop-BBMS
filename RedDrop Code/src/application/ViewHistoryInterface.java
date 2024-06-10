package application;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewHistoryInterface {
    private static final Random RANDOM = new Random();
    private Stage primaryStage;

    public ViewHistoryInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Create UI components
        Label titleLabel = new Label("          View History");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.BEIGE);
        titleLabel.setTranslateY(-80); // Adjust the vertical position

        ListView<String> historyListView = new ListView<>();
        historyListView.setItems(FXCollections.observableArrayList(
                generateRandomEntry(),
                generateRandomEntry(),
                generateRandomEntry(),
                generateRandomEntry(),
                generateRandomEntry()
        ));
        historyListView.setPrefHeight(300);
        historyListView.setPrefWidth(200);

        Button backButton = new Button("Back");
        backButton.setPrefWidth(150);
        backButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        backButton.setOnAction(e -> showCustomerInterface());

        // Create lines above and below the title
        Line lineAbove = new Line();
        lineAbove.setStartX(0);
        lineAbove.setEndX(300);
        lineAbove.setStyle("-fx-stroke: maroon;");
        lineAbove.setTranslateY(-80); // Adjust the vertical position
        Line lineBelow = new Line();
        lineBelow.setStartX(0);
        lineBelow.setEndX(300);
        lineBelow.setStyle("-fx-stroke: beige;");
        lineBelow.setTranslateY(-80); // Adjust the vertical position

        // Create UI layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setVgap(15);
        grid.add(lineAbove, 0, 0, 2, 1);
        grid.add(titleLabel, 0, 1, 2, 1);
        grid.add(lineBelow, 0, 2, 2, 1);
        grid.add(historyListView, 0, 3, 2, 1);
        grid.add(backButton, 0, 4, 2, 1);

        // Set grid background
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, null, null);
        grid.setBackground(new Background(backgroundFill));

        // Create a linear gradient background
        LinearGradient backgroundGradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.MAROON),
                new Stop(1, Color.BEIGE)
        );

        // Create VBox for overall layout
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(grid);

        // Set the background fill to the VBox
        vbox.setBackground(new Background(new BackgroundFill(backgroundGradient, null, null)));

        // Create scene and show stage
        Scene scene = new Scene(vbox, 1380, 700);
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Donation History");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generateRandomEntry() {
        int year = RANDOM.nextInt(2) + 2022; // Random year between 2022-2023
        int month = RANDOM.nextInt(12) + 1; // Random month between 1-12
        int day = RANDOM.nextInt(28) + 1; // Random day between 1-28
        int liters = RANDOM.nextInt(3) + 1; // Random liters between 1-3

        String date = String.format("%d-%02d-%02d", year, month, day);
        return String.format("%s: %d litre(s) blood donated", date, liters);
    }

    private void showCustomerInterface() {
        CustomerInterface customerInterface = new CustomerInterface(primaryStage);
        customerInterface.show();
    }
}
