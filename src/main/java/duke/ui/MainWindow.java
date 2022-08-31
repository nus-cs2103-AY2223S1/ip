package duke.ui;

import java.util.Objects;

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
 */
public class MainWindow extends AnchorPane {
    public static final String INTRO = "Welcome to Apollo!\n"
            + "How can I help you today?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sends welcome message to user.
     */
    public void sendWelcome() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(INTRO, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's
     * reply and then appends them to the dialog container. Clears the user input
     * after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String output = duke.execute(input);
        if (output.equals("Screen cleared!")) {
            dialogContainer.getChildren().clear();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(output, dukeImage)
        );
        if (output.equals("Goodbye, see you soon!")) {
            System.exit(0);
        }
        userInput.clear();
    }
}
