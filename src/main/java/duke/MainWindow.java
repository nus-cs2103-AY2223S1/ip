package duke;

import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Shows Duke's welcome message.
     */
    public void showWelcome() {
        String content;
        content = "Hello! I'm Echo" + System.lineSeparator() + "What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(content, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
//        assert !response.equals("") && !response.equals(" ") : "response should not be empty";
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            });
        }
        userInput.clear();
    }
}
