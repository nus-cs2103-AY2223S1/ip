package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/NotSoPoliteCat.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/PoliteCat.png"));

    /**
     * Initialises the dialog container and scroll pane
     */
    @FXML
    public void initialize() {
        dialogContainer.setPrefHeight(100);
        dialogContainer.setMaxHeight(100);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Our lovely Duke gives a sweet intro
     */
    public void sayHi() {
        String salutation = "A very good meow-ning to you, fellow cat!\n"
                + "I'm PoliteDuke, your friendly chat-bot!\n"
                + "What can I do for you? :D\n";

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(salutation, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
