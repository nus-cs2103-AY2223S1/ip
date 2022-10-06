package duke.gui;

import duke.Duke;
import duke.ui.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html#using-controllers
 * with minor modifications.
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

    private Duke duke;

    // Profile pictures of user and duke
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Initialize the Main Window.
     *
     * Note that initialize() is called first before setDuke().
     * Hence, the duke attribute is still null when this function is called.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    public void setDuke(Duke d) {
        duke = d;

        // Initialize Duke
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.initialize(), dukeImage));

        // Print welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.getWelcomeMessage(), dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        // Get and echo user input before processing it
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        // Process input and print response
        String response = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

}
