# Blood Donation System

This repository contains the code for a Blood Donation System, which can be used by an individual donor or a registered blood bank, the code includes various interfaces for scheduling appointments, viewing donation history, viewing blood results, and generating optimal meal plans (Customer interfaces), viewing inventory interface, send reminders, enable geotargeting, maintain transfusion reaction, enable cross search interface, donation record (blood bank interface) The system is built using JavaFX for the graphical user interface.

## Contents

The repository contains the following files:

1. `LoginApp.java` - Implements the login functionality for the Blood Donation System.
2. `CustomerInterface.java` - Represents the main interface for blood donors, providing access to various features.
3. `SetAppointmentInterface.java` - Implements the interface for scheduling appointments.
4. `ViewHistoryInterface.java` - Implements the interface for viewing donation history.
5. `ViewResultsInterface.java` - Implements the interface for viewing blood test results.
6. `ViewOptimalMealInterface.java` - Implements the interface for generating and viewing optimal meal plans.
7. `ViewInventoryInterface.java` - Implements the interface for viewing inventory.
8. `ViewdonationrecordInterface.java` - Implements the interface for viewing donation record for a specific donor.
9. `EnablecrossInventoryInterface.java` - Implements the interface for enabling interface to search a product in different banks.
10. `EnableGeotargetingInterface.java` - Implements the interface for sending alerts based on loactions.
11. `SendRemindersInterface.java` - Implements the interface to send reminders to donor.
12. `MaintaintransfusionreactoionInterface.java` - Implements the interface for enabling interface to maintain record for tranfusion reactions of a donor.
13. `Main.java` - Contains the entry point for running the application.

## Requirements

To run the Blood Donation System, make sure you have the following installed:

1. Java Development Kit (JDK) - version 8 or higher.
2. JavaFX - version 8 or higher.

## How to Run

Follow these steps to run the Blood Donation System:

1. Clone or download the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Build the project to compile all the source files.
4. Run the `Main.java` file to start the application.
5. The login screen will appear. Enter the appropriate credentials to access the customer interface.
6. Once logged in, you can navigate through the different features using the provided buttons.

## Additional Notes

- The code provided is a simplified representation of the Blood Donation System and may require further enhancements and validations to be suitable for production use.
- The application utilizes JavaFX for the graphical user interface. Ensure that your Java IDE is properly configured with JavaFX libraries to run the application successfully.
- Some code snippets have been modified or enhanced for styling and alignment purposes as requested.

## License

This project is licensed under the [MIT License](LICENSE).

