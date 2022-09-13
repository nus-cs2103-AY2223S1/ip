package dobby.ui;

import dobby.Dobby;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * A controller for MainWindow. Provides the layout for the other controls.
 *
 * <p> Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a> </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Dobby dobby;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/harry.png"));
    private Image dobbyImage = new Image(this.getClass().getResourceAsStream("/images/dobby.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke object.
     *
     * @param d duke object
     */
    public void setDobby(Dobby d) {
        dobby = d;
        d.setOut();
        printGreeting();
    }

    /**
     * Creates two dialog boxes, one to contain the user's input and the other containing Dobby's reply
     * They are then appended to the dialog container.
     * User input is cleared after processing for future dialogs.
     * Pause is added before closing application to allow users to see the "farewell" message from Dobby.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dobby.getResponse(input);
        if (response.equals("bye")) {
            response = "Byebye. Dobby will miss you!\n" + "Dobby will be leaving now....";

            PauseTransition termination = new PauseTransition(Duration.seconds(2d));
            termination.setOnFinished(event -> Platform.exit());
            termination.play();
        }
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                DobbyDialogBox.getDobbyDialog(response, dobbyImage)
        );
        userInput.clear();
    }

    /**
     * Prints the help message containing list of commands Dobby can understand.
     */
    @FXML
    private void printCommands() {
        String response = dobby.getCommands();
        dialogContainer.getChildren().addAll(
                DobbyDialogBox.getDobbyDialog(response, dobbyImage)
        );
    }

    /**
     * Prints the Dobby's greetings once application is booted up.
     */
    private void printGreeting() {
        String response = dobby.getGreetings();
        dialogContainer.getChildren().addAll(
                DobbyDialogBox.getDobbyDialog(response, dobbyImage)
        );
    }
}
