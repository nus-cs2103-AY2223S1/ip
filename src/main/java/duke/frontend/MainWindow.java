package duke.frontend;

import duke.Duke;
import duke.Parser;
import duke.Ui;
import duke.command.Command;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/remy.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/linguini.png"));

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = new Ui().getGreetingMessage();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetingMessage, dukeImage)
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        Command command = Parser.parse(input);
        if (command.isExit()) {
            this.handleExit();
        }
        userInput.clear();
    }

    @FXML
    private void handleExit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}
