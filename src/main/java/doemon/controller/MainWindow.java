package doemon.controller;

import doemon.Doemon;
import doemon.exception.DoemonException;
import doemon.response.Response;
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

    private Doemon doemon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nota.png"));
    private Image doemonImage = new Image(this.getClass().getResourceAsStream("/images/doemon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDoemonDialog(Response.welcomeString(), doemonImage));
    }

    public void setDoemon(Doemon d) {
        doemon = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Doemon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = doemon.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDoemonDialog(response, doemonImage)
            );
        } catch (DoemonException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDoemonDialog(e.toString(), doemonImage)
            );
        }
        userInput.clear();
    }
}
