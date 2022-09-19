package duke.ui;

import duke.Duke;
import duke.Response;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        boolean isLoadFileSuccess = duke.loadFile();
        if (isLoadFileSuccess) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                    "Hello! I'm Duke\n What can I do for you?", dukeImage));
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                    "Unable to load data", dukeImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        String message = response.getMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(message, dukeImage)
        );
        userInput.clear();
        if (response.isExit()) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}