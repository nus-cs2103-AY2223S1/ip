package duke;

import duke.ui.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import static duke.ui.Messages.MESSAGE_WELCOME;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(MESSAGE_WELCOME, dukeImage));
    }

    void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        //@@author LikHern-reused
        //Reused from https://stackoverflow.com/questions/39235545/add-delay-after-platform-runlater-runnable
        // with minor modifications
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0), e -> displayUserDialogAndDukeDialog(input, response));
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), e -> exitApplication());
        Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
        //@@author
    }

    @FXML
    private void displayUserDialogAndDukeDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }

    @FXML
    private void exitApplication() {
        if (Ui.isExit()) {
            Platform.exit();
        }
    }
}
