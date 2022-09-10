package duke.ui;

import java.util.HashMap;
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
    public static final String INTRO = "Welcome to Artemis!\n"
            + "How can I help you today?\n"
            + "Hint: Type \"help\" to get a list of available commands!";
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
    private final Image errorImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaError.png")));
    private final Image successImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaSuccess.png")));
    private final Image successYellowImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/DaSuccessYellow.png")));

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
        assert !output.equals("") : "Execution output should never be empty.";
        if (input.toLowerCase().startsWith("clear")) {
            dialogContainer.getChildren().clear();
        }
        if (input.toLowerCase().startsWith("help")) {
            HelpPopup.display();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(output, getDukeImage(output)));
        userInput.clear();
    }

    /**
     * Returns an appropriate image for the output string.
     *
     * @param output the string returned by Artemis
     * @return the image corresponding to the output
     */
    private Image getDukeImage(String output) {
        HashMap<String, Image> images = new HashMap<>() {{
                put("Hang on!", errorImage);
                put("Success!", successImage);
                put("Whoops!", successYellowImage);
            }};
        for (String start : images.keySet()) {
            if (output.startsWith(start)) {
                return images.get(start);
            }
        }
        return dukeImage;
    }
}
