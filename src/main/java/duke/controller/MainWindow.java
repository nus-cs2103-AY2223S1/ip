package duke.controller;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window
 *
 * @author Pontakorn Prasertsuk
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    /**
     * Initializes the main window on rendered by the JavaFX application
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog("Hello from\n" + "______       _     \n"
                        + "| ___ \\     | |    \n" + "| |_/ / ___ | |__  \n"
                        + "| ___ \\/ _ \\| '_ \\ \n" + "| |_/ / (_) | |_) |\n"
                        + "\\____/ \\___/|_.__/ \n" + "What can I do for you?", dukeImage));
    }

    /**
     * Sets the Duke instance for the application
     *
     * @param duke the Duke instance
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
