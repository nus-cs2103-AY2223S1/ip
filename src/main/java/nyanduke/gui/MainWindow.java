package nyanduke.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nyanduke.NyanDuke;

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

    private NyanDuke nyanDuke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Doge.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/NyanCat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNyanDuke(NyanDuke nyanDuke) {
        this.nyanDuke = nyanDuke;
    }

    /**
     * Shows the welcome message from Duke.
     */
    public void showWelcome() {
        String welcome = nyanDuke.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing NyanDuke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nyanDuke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (nyanDuke.isExit()) {
            Platform.exit();
        }
        userInput.clear();
    }
}
