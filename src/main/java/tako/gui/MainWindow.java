package tako.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import tako.Tako;

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

    private Tako tako;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image takoImage = new Image(this.getClass().getResourceAsStream("/images/DaTako.png"));

    /**
     * Initializes the scrollPane to scroll down automatically when dialog is too long.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTako(Tako t) {
        tako = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tako's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the user inputs the bye command then the program will close.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tako.getResponse(input);
        if (tako.isExit()) {
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getTakoDialog(response, takoImage)
            );
        }
        userInput.clear();
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        String welcome = tako.getWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getTakoDialog(welcome, takoImage)
        );
    }
}
