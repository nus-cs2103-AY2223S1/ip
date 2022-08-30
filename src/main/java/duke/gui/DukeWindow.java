package duke.gui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The Duke Window class.
 */
public class DukeWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final String userImagePath = "/images/student.jpeg";
    private final String dukeImagePath = "/images/genie.jpeg";

    private final Image userImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream(userImagePath)));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(
                    this.getClass().getResourceAsStream(dukeImagePath)));

    /**
     * Duke Window class constructor.
     *
     * @param duke
     *            The given duke object.
     */
    public DukeWindow(Duke duke) {
        try {
            String dukeWindowPath = "/view/DukeWindow.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(
                    this.getClass().getResource(dukeWindowPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.duke = duke;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The initialize function is called when the FXML file is loaded.
     * It sets up the dialog box and its contents, including all of its buttons.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DukeDialogBox.getUserDialog(input, userImage),
                DukeDialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
