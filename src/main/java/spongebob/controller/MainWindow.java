package spongebob.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import spongebob.SpongebobApplication;

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

    private SpongebobApplication duke;
    private Image spongebobImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private Image patrickImage = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(SpongebobApplication d) {
        this.duke = d;
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hey Patrick!\nWhat do you want to do today?", spongebobImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, patrickImage));
        String response = this.duke.process(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, spongebobImage));
        userInput.clear();
    }
}
