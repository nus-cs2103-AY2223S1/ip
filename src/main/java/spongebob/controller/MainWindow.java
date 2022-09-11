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

    private SpongebobApplication spongebobApplication;
    private Image spongebobImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private Image patrickImage = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSpongebobApplication(SpongebobApplication app) {
        this.spongebobApplication = app;
        String welcomeMsg = "Hey Patrick! Here are some things you can do:"
                + "\n  1) list"
                + "\n  2) todo <task>"
                + "\n  3) deadline <task> /by <yyyy-mm-dd>"
                + "\n  4) event <event> /at <yyyy-mm-dd>"
                + "\n  5) mark <num>"
                + "\n  6) unmark <num>"
                + "\n  7) find <keyword>"
                + "\n  8) bye"
                + "\nWhat do you want to do today?";
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg, spongebobImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, patrickImage));
        String response = this.spongebobApplication.process(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, spongebobImage));
        userInput.clear();
    }
}
