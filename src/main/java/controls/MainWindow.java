package controls;

import java.util.Objects;

import duke.Duke;
import exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class MainWindow extends AnchorPane {
    //@@author mjgui-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private final Image dukeLaserImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDukeLaser.png")));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Duke duke;

    /**
     * Runs upon initialization of MainWindow class.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog(Duke.getWelcomeMessage(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            Pair<String, Boolean> response = duke.getResponse(input);
            String responseString = response.getFirst();
            boolean isExit = response.getSecond();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(responseString, dukeImage)
            );
            if (isExit) {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            }
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("ERROR!! " + e.getMessage(), dukeLaserImage)
            );
        } finally {
            userInput.clear();
        }
    }
    //@@author
}
