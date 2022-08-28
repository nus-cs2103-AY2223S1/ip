package duke.controllers;

import java.util.List;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Code adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html
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
    @FXML
    private InputBox inputBoxController;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initalizes MainWindow and its composed objects.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        inputBoxController.setImages(userImage, dukeImage);
        inputBoxController.setDialogContainer(dialogContainer);
    }

    /**
     * Sets Duke object in {@code inputBox}.
     *
     * @param d Duke object to process the inputs.
     */
    public void setDuke(Duke d) {
        duke = d;
        inputBoxController.setDuke(d);
    }

    /**
     * Starts duke by loading saved tasks and displaying a welcome message and load
     * tasks results.
     */
    public void showWelcome() {
        List<String> startMessages = duke.start();
        startMessages.forEach((message) -> dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImage)));
    }
}
