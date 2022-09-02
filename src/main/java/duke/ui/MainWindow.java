package duke.ui;

import static duke.Duke.TAB;

import java.util.Objects;

import duke.Duke;
import duke.util.Response;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Formatter {

    private static final String DUKE_AVATAR_PATH = "/images/DaUser.png";
    private static final String USER_AVATAR_PATH = "/images/DaDuke.png";

    private Duke duke;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private boolean isExit = false;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(DUKE_AVATAR_PATH)));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(USER_AVATAR_PATH)));

    /**
     * Binds the object in code with the .xml file.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Binds a chat bot to this window.
     *
     * @param duke An instance of Duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(formatOutput(response.getMessage()), dukeImage)
        );
        userInput.clear();
        isExit = response.isExit();
    }

    /**
     * Public getter of isExit.
     *
     * @return Boolean indicating whether the program is about to terminate.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Takes in a raw string and formats it.
     *
     * @param input String that is raw.
     * @return String formatted and is about to be printed on some screen output.
     */
    @Override
    public String formatOutput(String input) {
        return TAB + input;
    }
}
