package duke.gui;

import duke.Duke;
import duke.helper.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/phil.jpg"));

    /**
     * Method to initialize the gui
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox messageBox = DialogBox.getDukeDialog(Ui.welcome(), dukeImage);
        messageBox.setTranslateY(10.0);
        messageBox.setTranslateX(5.0);
        dialogContainer.getChildren().addAll(messageBox);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = duke.getResponse(input);
        } catch (AssertionError e) {
            response = e.getMessage();
        }

        DialogBox userBox = DialogBox.getUserDialog(input + "   ", userImage);
        DialogBox dukeBox = DialogBox.getDukeDialog(response, dukeImage);
        userBox.setTranslateY(10.0);
        userBox.setTranslateX(5.0);
        dukeBox.setTranslateY(10.0);
        dukeBox.setTranslateX(5.0);
        dialogContainer.getChildren().addAll(userBox, dukeBox);

        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }
}
