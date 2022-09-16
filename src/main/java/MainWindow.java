//Class below reuse code from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modification
package anya;

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

    private Anya anya;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    private Image anyaWelcomeImage = new Image(this.getClass().getResourceAsStream("/images/AnyaWelcome.png"));
    private Image anyaErrorImage = new Image(this.getClass().getResourceAsStream("/images/AnyaERROR2.png"));
    private Image anyaRespondImage = new Image(this.getClass().getResourceAsStream("/images/Anya.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAnya(Anya a) {
        anya = a;
        String greet = a.start();
        dialogContainer.getChildren().addAll(
            DialogBox.getAnyaDialog(greet, anyaWelcomeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Anya's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Ends chatbot when the user's input is bye.
     * @throws IOException.
     * @throws AnyaException.
     */
    @FXML
    private void handleUserInput() throws IOException, AnyaException {
        String input = userInput.getText();
        String response = anya.getResponse(input);
        if (anya.getHasReturnError() == false) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAnyaDialog(response, anyaRespondImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, anyaErrorImage)
            );
        }

        if (input.equals("bye")) {
            System.exit(0);
        }

        userInput.clear();

    }
}
