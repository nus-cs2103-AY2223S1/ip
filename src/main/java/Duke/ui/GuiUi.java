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

    /**
     * Set a Duke for GuiUi.
     *
     * @param duke a Duke for GuiUi.
     */
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

        boolean isValidInput = !input.trim().isBlank();
        if (isValidInput) {
            displayInput(input);
            output = duke.receiveInput(input);
        }

        boolean isValidOutput = output != null;
        if (isValidOutput) {
            displayOutput(output);
        }

        userInput.clear();
    }

    /**
     * Display the user input to the user.
     */
    public void displayInput(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
    }

    /**
     * Display the Duke output to the user.
     */
    public void displayOutput(String output) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(output, dukeImage)
        );
    }
}
