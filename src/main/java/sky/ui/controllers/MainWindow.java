package sky.ui.controllers;

import sky.Sky;

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

    private Sky sky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Weed.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Schooling.png"));

    /**
     * Initialize the greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = "Hello from Sky!\nYour heavenly chatbot to help you track your things.\n" +
                "Type something to get started!";
        dialogContainer.getChildren().addAll(DialogBox.getSkyDialog(greetingMessage, dukeImage));
    }

    public void setSky(Sky s) {
        sky = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkyDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals("Bye. May all your endeavours fly high!")) {
            Platform.exit();
        }
    }
}
