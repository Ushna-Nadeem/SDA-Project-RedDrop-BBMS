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


public class CustomerInterface {
    private Stage primaryStage;

    public CustomerInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        Text welcomeText = new Text("Welcome, Donor!");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Button setAppointmentButton = new Button("Schedule Appointment");
        Button viewHistoryButton = new Button("View Donation History");
        Button viewResultsButton = new Button("View Blood Results");
        Button viewOptimalMealButton = new Button("Generate and View Optimal Meal");
        Button logoutButton = new Button("Logout");

        // Style the buttons
        setButtonStyle(setAppointmentButton);
        setButtonStyle(viewHistoryButton);
        setButtonStyle(viewResultsButton);
        setButtonStyle(viewOptimalMealButton);
        setButtonStyle(logoutButton);

        // Add event handlers for the customer interface buttons
        setAppointmentButton.setOnAction(e -> {
            SetAppointmentInterface setAppointmentInterface = new SetAppointmentInterface(primaryStage);
            setAppointmentInterface.show();
        });
        viewHistoryButton.setOnAction(e -> {
            ViewHistoryInterface viewHistoryInterface = new ViewHistoryInterface(primaryStage);
            viewHistoryInterface.show();
        });
        viewResultsButton.setOnAction(e -> {
            ViewResultsInterface viewResultsInterface = new ViewResultsInterface(primaryStage);
            viewResultsInterface.show();
        });
        viewOptimalMealButton.setOnAction(e -> {
            ViewOptimalMealInterface viewOptimalMealInterface = new ViewOptimalMealInterface(primaryStage);
            viewOptimalMealInterface.show();
        });
        logoutButton.setOnAction(e -> showLogOutInterface());

        VBox buttonsVBox = new VBox(40, setAppointmentButton, viewHistoryButton, viewResultsButton, viewOptimalMealButton, logoutButton);
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

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: maroon; -fx-text-fill: #FFFFFF; -fx-font-size: 16px;");
    }
    
    private void showLogOutInterface() {
        LoginApp logininterface = new LoginApp();
        logininterface.start(primaryStage);
    }
}