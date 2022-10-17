package duke.gui;

import duke.Duke;
import duke.commons.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author L1Y1jun-reused
//Adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    /**
     * Initialises the FXML controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
    }

    public void setGui(Duke duke) {
        this.duke = duke;
    }

    /**
     * Initiates a greeting when the application is launched.
     */
    public void sayGreetings() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.formatGreetingMessage(), botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, botImage)
        );
        double height = userInput.getMaxHeight();
        dialogContainer.setPrefHeight(height);
        userInput.clear();
    }
}
//@@author
