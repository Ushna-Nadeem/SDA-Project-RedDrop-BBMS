package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class ViewResultsInterface {
    private static final Random RANDOM = new Random();
    private Stage primaryStage;

    public ViewResultsInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Create UI components
        Label titleLabel = new Label("              View Blood Test");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.BEIGE);
        titleLabel.setTranslateY(-80); // Adjust the vertical position

        // Add line above and below the title
        Line lineAboveTitle = new Line();
        lineAboveTitle.setStartX(0);
        lineAboveTitle.setEndX(400);
        lineAboveTitle.setStroke(Color.MAROON);
        lineAboveTitle.setTranslateY(-80); // Adjust the vertical position
        Line lineBelowTitle = new Line();
        lineBelowTitle.setStartX(0);
        lineBelowTitle.setEndX(400);
        lineBelowTitle.setStroke(Color.BEIGE);
        lineBelowTitle.setTranslateY(-80); // Adjust the vertical position

        Label nameLabel = new Label("Username:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField passwordTextField = new TextField();
        Label testTypeLabel = new Label("Blood Test Name:");
        testTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> testTypeComboBox = new ComboBox<>();
        testTypeComboBox.getItems().addAll("Complete Blood Count", "Coagulation Panel", "Thyroid Function Test", "Comprehensive Metabolic Panel", "Lipid Panel", "Basic Metabolic Panel", "Blood Culture", "Blood Typing");
        Label bankLabel = new Label("Blood Bank:");
        bankLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField bankTextField = new TextField();
        Label dateLabel = new Label("Test Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField dateTextField = new TextField();
        Button showButton = new Button("Fetch Blood Test Result");
        showButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #8B0000;");

        // Create UI layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setBackground(createBackground());
        grid.add(lineAboveTitle, 0, 0, 2, 1); // Add line above the title
        grid.add(titleLabel, 0, 1, 2, 1);
        grid.add(lineBelowTitle, 0, 2, 2, 1); // Add line below the title
        grid.add(nameLabel, 0, 3);
        grid.add(nameTextField, 1, 3);
        grid.add(passwordLabel, 0, 4);
        grid.add(passwordTextField, 1, 4);
        grid.add(testTypeLabel, 0, 5);
        grid.add(testTypeComboBox, 1, 5);
        grid.add(bankLabel, 0, 6);
        grid.add(bankTextField, 1, 6);
        grid.add(dateLabel, 0, 7);
        grid.add(dateTextField, 1, 7);
        grid.add(showButton, 1, 8);

        // Fetch Blood Test Result button event handler
        showButton.setOnAction(event -> {
            String testType = testTypeComboBox.getValue();
            // Generate random blood test result
            String bloodTestResult = generateRandomBloodTestResult(testType);

            // Display blood test result
            displayBloodTestResult(bloodTestResult);
        });

        // Create scene and show stage
        Scene scene = new Scene(grid, 1380, 700);
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Blood Results");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generateRandomBloodTestResult(String testType) {
        // Generate a random blood test result based on the selected test type
        // Replace this logic with your own blood test result generation code
        String bloodTestResult = "Blood Test: " + testType + "\n\n";
        bloodTestResult += "Result: ";
        int randomValue = RANDOM.nextInt(3); // Generate a random value (0, 1, or 2)
        if (randomValue == 0) {
            bloodTestResult += "Normal";
        } else if (randomValue == 1) {
            bloodTestResult += "Abnormal";
        } else {
            bloodTestResult += "Inconclusive";
        }
        return bloodTestResult;
    }

    private void displayBloodTestResult(String bloodTestResult) {
        // Display the blood test result
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Blood Test Result");
        alert.setHeaderText(null);
        alert.setContentText(bloodTestResult);
        alert.showAndWait();
        showCustomerInterface(primaryStage);
    }

    private void showCustomerInterface(Stage primaryStage) {
        CustomerInterface customerInterface = new CustomerInterface(primaryStage);
        customerInterface.show();
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
