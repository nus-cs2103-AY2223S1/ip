package duke.gui;

import java.util.Objects;

import duke.Duke;
import duke.DukeException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void showWelcome() {
        String welcome = duke.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (userInput.isDisabled()) {
            return;
        }
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    dukeDialog
            );
            if (duke.hasTasksEnd()) {
                userInput.setDisable(true);
                final IntegerProperty i = new SimpleIntegerProperty(0);
                Timeline timeline = new Timeline(
                    new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            i.set(i.get() + 1);
                            if (i.get() == 3) {
                                Platform.exit();
                            }
                            dukeDialog.setText(response.substring(0, response.length() - 1) + (3 - i.get()));
                        }
                    )
                );
                timeline.setCycleCount(3);
                timeline.play();
            }
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }
        userInput.clear();
    }
}
