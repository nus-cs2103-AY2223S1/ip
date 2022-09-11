package duke.gui;

import duke.main.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final int TIME_TO_CLOSE = 3;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object which controls the MainWindow.
     * @param d The Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
        duke.getUi().setMainWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        duke.handleInput(input);
        userInput.clear();
    }

    /**
     * Adds Duke's dialog to MainWindow.
     * @param message Message to be added.
     */
    public void addDukeDialog(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    /**
     * Closes the window of Duke.
     */
    public void closeWindow() {
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(TIME_TO_CLOSE));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }
}
