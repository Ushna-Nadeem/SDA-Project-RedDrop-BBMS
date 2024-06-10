package com.example.thethingsproject;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;





public class BMS extends Application {
    private Login login;
    private Donor loggedinDonor;

    private RequestHandler requestHandler;

    private GoogleMapsAPI googleMapsAPI;

    private PersistenceHandler persistenceHandler;

    private Boolean alarmSelected = false;

    public BMS() {
        googleMapsAPI = new GoogleMapsAPI();
        requestHandler = new RequestHandler();
    }


    public void start(Stage stage) throws IOException {
        stage.setTitle("Blood Bank Management System");

        VBox menu = new VBox(); //Vertical box layout
        menu.setSpacing(10); //Spacing between items in the menu


        Label title = new Label("Welcome to the Blood Bank Management System");
        if (loggedinDonor != null) { //Post login code
            title.setText("Welcome " + loggedinDonor.getUsername());
        }
        title.setFont(new javafx.scene.text.Font("Arial", 20)); //Setting font and size of title
        menu.getChildren().add(title);

        Button loginButton = new Button("Login");

        if (loggedinDonor != null) { //Post login code
            System.out.println("Since donor is logged in, retrieving donor's location");
            Label locationLabel = new Label("Your current location is " + googleMapsAPI.checkLocation());
            menu.getChildren().add(locationLabel);
            loggedinDonor.setCurrentLocation(googleMapsAPI.checkLocation());
            loggedinDonor.setCity(googleMapsAPI.checkCity());

            ListView<String> listView = new ListView<String>();

            List<String> city_hospital_list = requestHandler.returnRequestsByCity(loggedinDonor.getCity());
            listView.getItems().addAll(city_hospital_list);

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event)
                {
                    String selectedRequest = listView.getSelectionModel().getSelectedItem();
                    System.out.println("Selected request: " + selectedRequest);
                    goToDonate(selectedRequest);

                }
            });

            Button appointmentButton = new Button("Click here for general appointment");
            appointmentButton.setOnAction(e -> {


                goToAppointment();

            });



            menu.getChildren().add(listView);
            menu.getChildren().add(appointmentButton);

        }

        else //Post login code
        {
            menu.getChildren().add(loginButton);
        }
        menu.setAlignment(javafx.geometry.Pos.CENTER); //Centering the menu (vertical and horizontal). Everything becomes centered. Before this login would be in the top left corner.

        Scene scene = new Scene(menu, 500, 200); //Width, Height
        stage.setScene(scene);
        stage.show();

        login = new Login();

        loginButton.setOnAction(e -> {
            try {
                displayLogin(stage);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void displayLogin(Stage stage) throws IOException {
        login.setLoginCallback(this:: onLoginSuccess);
        login.start(stage);
    }

    private void onLoginSuccess(Donor donor) {
        loggedinDonor = donor;
        System.out.println("Logged in as " + donor.getUsername());
        try {
            start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToDonate(String selectedRequest) {


        persistenceHandler = new PersistenceHandler();
        Request request = requestHandler.returnRequestByLocation(selectedRequest);
        Stage donateState = new Stage();
        donateState.setTitle("Donate");

        VBox donateMenu = new VBox(); //Vertical box layout
        donateMenu.setSpacing(10); //Spacing between items in the menu

        Label title = new Label("Following are the details for the donation:");
        title.setFont(new javafx.scene.text.Font("Arial", 20)); //Setting font and size of title

        Label requestLocation = new Label("Location: " + selectedRequest);

        Label requestBloodType = new Label("Blood Type: " + request.getBloodGroup());

        Label requestCity = new Label("City: " + request.getCity());

        requestLocation.setFont(new javafx.scene.text.Font("Arial", 15)); //Setting font and size of title
        requestBloodType.setFont(new javafx.scene.text.Font("Arial", 15)); //Setting font and size of title
        requestCity.setFont(new javafx.scene.text.Font("Arial", 15)); //Setting font and size of title

        donateMenu.getChildren().addAll(title, requestLocation, requestBloodType, requestCity);

        Reaction reaction = persistenceHandler.checkForReaction(loggedinDonor.getUsername());

        if (reaction == null)
        {
            Button donateButton = new Button("Donate");
            donateMenu.getChildren().add(donateButton);

            donateButton.setOnAction(e -> {
                System.out.println("Blood will be donated. End of scope here");
            });
        }

        else
        {
            Label warning = new Label("You have previously shown a reaction to a donation. You cannot donate again.");
            Label date = new Label("Date of reaction: " + reaction.getDate());
            Label description = new Label("Description of reaction: " + reaction.getReaction());

            Button returnButton = new Button("Return");
            donateMenu.getChildren().addAll(warning, date, description, returnButton);

            returnButton.setOnAction(e -> {
                try {
                    loggedinDonor = null;

                    start(new Stage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        Scene donateScene = new Scene(donateMenu, 500, 200); //Width, Height

        donateState.setScene(donateScene);
        donateState.show();

    }

    public void goToAppointment()
    {
        System.out.println("Going to appointment");
        Stage appointmentStage = new Stage();
        appointmentStage.setTitle("Appointment");

        VBox appointmentMenu = new VBox(); //Vertical box layout
        appointmentMenu.setSpacing(10); //Spacing between items in the menu

        CheckBox alarm = new CheckBox("Turn on alarm notifications");


        alarm.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if (new_val) {
                    System.out.println("Alarm is on");
                    alarmSelected = true;
                }
                else
                {
                    System.out.println("Alarm is off");
                    alarmSelected = false;
                }
            }
        });

        appointmentMenu.getChildren().add(alarm);



        Label title = new Label("Following hospitals are available for appointment:");
        title.setFont(new javafx.scene.text.Font("Arial", 20)); //Setting font and size of title

        ListView<String> listView = new ListView<String>();

        List<String> city_hospital_list = requestHandler.returnRequestsByAppointment();

        listView.getItems().addAll(city_hospital_list);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                String selectedRequest = listView.getSelectionModel().getSelectedItem();
                System.out.println("Selected request: " + selectedRequest);
                goToAppointmentDetails(selectedRequest);
            }
        });

        appointmentMenu.getChildren().addAll(title, listView);

        Scene appointmentScene = new Scene(appointmentMenu, 500, 200); //Width, Height

        appointmentStage.setScene(appointmentScene);
        appointmentStage.show();

    }

    public void goToAppointmentDetails(String selectedRequest)
    {
        System.out.println("Going to appointment details");
        Stage appointmentDetailsStage = new Stage();
        appointmentDetailsStage.setTitle("Appointment Details");

        VBox appointmentDetailsMenu = new VBox(); //Vertical box layout
        appointmentDetailsMenu.setSpacing(10); //Spacing between items in the menu

        Label time = new Label("Pick a time: ");
        ComboBox<String> timeComboBox = new ComboBox<>();

        timeComboBox.getItems().addAll(

                "9/6/2021 11:00",
                "9/6/2021 12:00",
                "9/6/2021 13:00",
                "9/6/2021 14:00"

        );

        timeComboBox.setOnAction(e -> {
            String selectedTime = timeComboBox.getValue();
            System.out.println("Selected date: " + selectedTime);
        });

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            System.out.println("Appointment confirmed");
            if (alarmSelected)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alarm");
                alert.setHeaderText("Alarm is on");
                alert.setContentText("Your appointment is set for " + timeComboBox.getValue() + ". You will be notified 1 hour before the appointment.");
                alert.showAndWait();
            }
            //Add appointment to database
            persistenceHandler = new PersistenceHandler();
            try {
                persistenceHandler.addAppointment(loggedinDonor.getUsername(), selectedRequest, timeComboBox.getValue());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        appointmentDetailsMenu.getChildren().addAll(time, timeComboBox, confirmButton);

        Scene appointmentDetailsScene = new Scene(appointmentDetailsMenu, 500, 200); //Width, Height

        appointmentDetailsStage.setScene(appointmentDetailsScene);

        appointmentDetailsStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


