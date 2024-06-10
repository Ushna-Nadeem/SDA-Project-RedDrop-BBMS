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

public class ViewOptimalMealInterface {
    private Stage primaryStage;

    public ViewOptimalMealInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
    	// Create UI components
        Label titleLabel = new Label("      View and Generate Optimal Meal");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.BEIGE);
        titleLabel.setTranslateY(-80); // Adjust the vertical position

        // Add line above and below the title
        Line lineAboveTitle = new Line();
        lineAboveTitle.setStartX(0);
        lineAboveTitle.setEndX(500);
        lineAboveTitle.setTranslateY(-80); // Adjust the vertical position
        lineAboveTitle.setStroke(Color.MAROON);
        Line lineBelowTitle = new Line();
        lineBelowTitle.setStartX(0);
        lineBelowTitle.setEndX(500);
        lineBelowTitle.setStroke(Color.BEIGE);
        lineBelowTitle.setTranslateY(-80); // Adjust the vertical position
        

        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameTextField = new TextField();
        Label ageLabel = new Label("Age: ");
        ageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField ageTextField = new TextField();
        Label genderTypeLabel = new Label("Gender: ");
        genderTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> genderTypeComboBox = new ComboBox<>();
        genderTypeComboBox.getItems().addAll("Male", "Female", "Other");
        Label weightLabel = new Label("Weight: ");
        weightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField weightTextField = new TextField();
        Label heightLabel = new Label("Height: ");
        heightLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField heightTextField = new TextField();
        Label allergiesLabel = new Label("Allergies (if any): ");
        allergiesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField allergiesTextField = new TextField();
        Button showButton = new Button("Show");
        showButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #8B0000;");

        // Create layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25));
        grid.setBackground(createBackground());
        grid.add(lineAboveTitle, 0, 0, 2, 1); // Add line above the title
        grid.add(titleLabel, 0, 1, 2, 1);
        grid.add(lineBelowTitle, 0, 2, 2, 1); // Add line below the title
        grid.add(nameLabel, 0, 3);
        grid.add(nameTextField, 1, 3);
        grid.add(ageLabel, 0, 4);
        grid.add(ageTextField, 1, 4);
        grid.add(genderTypeLabel, 0, 5);
        grid.add(genderTypeComboBox, 1, 5);
        grid.add(weightLabel, 0, 6);
        grid.add(weightTextField, 1, 6);
        grid.add(heightLabel, 0, 7);
        grid.add(heightTextField, 1, 7);
        grid.add(allergiesLabel, 0, 8);
        grid.add(allergiesTextField, 1, 8);
        grid.add(showButton, 1, 9);

        // Set event handler for the show button
        showButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String age = ageTextField.getText();
            String gender = genderTypeComboBox.getValue();
            String weight = weightTextField.getText();
            String height = heightTextField.getText();
            String allergies = allergiesTextField.getText();

            // Perform the optimal meal calculation and display the result
            String optimalMeal = calculateOptimalMeal(name, age, gender, weight, height, allergies);
            displayOptimalMealResult(optimalMeal);
        });

        Scene scene = new Scene(grid, 1380, 700);
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Optimal Meal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String calculateOptimalMeal(String name, String age, String gender, String weight, String height, String allergies) {
        // Perform the calculation logic here
        // Return the optimal meal as a string
        return "Sample Optimal Meal";
    }

    private void displayOptimalMealResult(String optimalMeal) {
        // Display the optimal meal result
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Optimal Meal Result");
        alert.setHeaderText(null);
        alert.setContentText("Your optimal meal plan is...\n\n" + optimalMeal);
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
                new Stop(0, Color.MAROON),
                new Stop(1, Color.BEIGE)
        );

        BackgroundFill backgroundFill = new BackgroundFill(backgroundGradient, null, null);
        return new Background(backgroundFill);
    }
}
