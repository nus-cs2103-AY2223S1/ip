package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents a controller for MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    private Image dukeWelcomeImage = new Image(this.getClass().getResourceAsStream("/images/AnyaWelcome.png"));
    private Image dukeErrorImage = new Image(this.getClass().getResourceAsStream("/images/AnyaError.png"));
    private Image dukeRespondImage = new Image(this.getClass().getResourceAsStream("/images/Anya.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        String greet = d.start();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(greet, dukeWelcomeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Ends chatbot when the user's input is bye.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (duke.getHasReturnError() == false) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeRespondImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, dukeErrorImage)
            );
        }

        if (input.equals("bye")) {
            System.exit(0);
        }

        userInput.clear();

    }
}
