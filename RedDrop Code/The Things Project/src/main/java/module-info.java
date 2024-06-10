module com.example.thethingsproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thethingsproject to javafx.fxml;
    exports com.example.thethingsproject;
}