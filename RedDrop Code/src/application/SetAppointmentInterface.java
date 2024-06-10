package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SetAppointmentInterface {
    private Stage primaryStage;
    private static final String APPOINTMENTS_FILE = "appointments.txt";

    public SetAppointmentInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("RedDrop -- Customer Homepage -- Appointments");

        // Create UI components
        Label titleLabel = new Label();
        titleLabel.setText("Schedule Appointment");
        titleLabel.setFont(Font.font("Arial", 26));
        titleLabel.setTextFill(Color.BEIGE);
        titleLabel.setTranslateY(-80); // Adjust the vertical position

        // Add lines above and below the title
        Separator separatorAbove = new Separator();
        separatorAbove.setPrefWidth(400);
        separatorAbove.setTranslateY(-80);
        separatorAbove.setStyle("-fx-background-color: beige;");

        Separator separatorBelow = new Separator();
        separatorBelow.setPrefWidth(400);
        separatorBelow.setTranslateY(-80);
        separatorBelow.setStyle("-fx-background-color: beige;");

        // Create layout
        VBox titleLayout = new VBox(separatorAbove, titleLabel, separatorBelow);
        titleLayout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(200);

        Label addressLabel = new Label("Address:");
        addressLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField addressTextField = new TextField();
        addressTextField.setPrefWidth(200);

        Label bloodTypeLabel = new Label("Blood Type:");
        bloodTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ComboBox<String> bloodTypeComboBox = new ComboBox<>();
        bloodTypeComboBox.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        Label dateLabel = new Label("Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField dateTextField = new TextField();
        dateTextField.setPrefWidth(200);

        Label timeLabel = new Label("Time:");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        TextField timeTextField = new TextField();
        timeTextField.setPrefWidth(200);

        Button submitButton = new Button("Schedule");
        submitButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #ffffff; -fx-background-color: #8B0000;");

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(25));
        gridPane.setBackground(createBackground());

        // Add UI components to the grid pane
        gridPane.add(titleLayout, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(addressTextField, 1, 2);
        gridPane.add(bloodTypeLabel, 0, 3);
        gridPane.add(bloodTypeComboBox, 1, 3);
        gridPane.add(dateLabel, 0, 4);
        gridPane.add(dateTextField, 1, 4);
        gridPane.add(timeLabel, 0, 5);
        gridPane.add(timeTextField, 1, 5);
        gridPane.add(submitButton, 1, 6, 2, 1);

        // Set event handler for the submit button
        submitButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String bloodType = bloodTypeComboBox.getValue();
            String date = dateTextField.getText();
            String time = timeTextField.getText();

            // Save appointment details to file
            saveAppointmentToFile(name, address, bloodType, date, time);

            // Display appointment scheduled message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Scheduled");
            alert.setHeaderText(null);
            alert.setContentText("Appointment scheduled for " + name + " on " + date);
            alert.showAndWait();
            showCustomerInterface();
        });

        Scene scene = new Scene(gridPane, 1380, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCustomerInterface() {
        CustomerInterface customerInterface = new CustomerInterface(primaryStage);
        customerInterface.show();
    }

    private void saveAppointmentToFile(String name, String address, String bloodType, String date, String time) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE, true))) {
            String appointmentLine = name + "," + address + "," + bloodType + "," + date + "," + time;
            writer.write(appointmentLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
