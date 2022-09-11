package Duke.ui;

import Duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class GuiUi extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    public Button sendButton;

    private Duke duke;

    private final Image userImage
            = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/cat.jpeg")));
    private final Image dukeImage
            = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/neko.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        duke.setGui(this);
    }

    /**
     * Creates two dialog boxes, one echoing user's input and the other containing Duke's reply.
     */
    public void handleUserInput() {
        String input = userInput.getText();
        String output = null;

        if (isValidInput(input)) {
            displayInput(input);
            output = duke.receiveInput(input);
        }

        if (isValidOutput(output)) {
            displayOutput(output);
        }

        userInput.clear();
    }

    private boolean isValidInput(String input) {
        return !input.trim().isBlank();
    }

    private boolean isValidOutput(String output) {
        return output != null;
    }

    public void displayInput(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
    }

    public void displayOutput(String output) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(output, dukeImage)
        );
    }
}
