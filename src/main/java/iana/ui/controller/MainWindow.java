package iana.ui.controller;
import iana.Iana;
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

    private Iana iana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image ianaImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));

    /**
     * Initialize main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialize Iana.
     */
    public void setIana(Iana i) {
        iana = i;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing iana's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = iana.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getIanaDialog(response, ianaImage)
        );
        userInput.clear();
    }
}