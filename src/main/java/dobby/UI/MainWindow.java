package dobby.UI;

import dobby.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.*;

import java.util.concurrent.*;

/**
 * A controller for MainWindow. Provides the layout for the other controls.
 * <p>
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button helpButton;
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
     * @param d duke object
     */
    public void setDobby(Dobby d) {
        dobby = d;
        d.setOut();
        printGreeting();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dobby.getResponse(input);
        if(response.equals("bye")) {
            response = "Byebye. Dobby will miss you!\n" + "Dobby will be leaving now....";

            PauseTransition termination = new PauseTransition(Duration.seconds(2d));
            termination.setOnFinished(event -> Platform.exit());
            termination.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dobbyImage)
        );
        userInput.clear();
    }

    /**
     * Prints the help message.
     */
    @FXML
    private void printHelp() {
        String response = dobby.getHelp();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dobbyImage)
        );
    }

    /**
     * Prints the greeting message.
     */
    private void printGreeting() {
        String response = dobby.getGreetings();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dobbyImage)
        );
    }
}
