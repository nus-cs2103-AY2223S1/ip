package bloop.gui;

import bloop.Bloop;
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

    private Bloop bloop;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Bao.png"));
    private Image bloopImage = new Image(this.getClass().getResourceAsStream("/images/Bloop.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBloop(Bloop d) {
        bloop = d;
        dialogContainer.getChildren().addAll(DialogBox.getBloopDialog("Heyy, what can I do for you?", bloopImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bloop.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBloopDialog(response, bloopImage)
        );
        userInput.clear();
    }


}
