package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow.
 *
 * @author Elgin
 */
public class MainWindow extends VBox {
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Setter for Duke.
     *
     * @param duke
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Duke\n" + "What can I do for you?", this.dukeImage)
        );
    }

    @FXML
    private void submitHandler() {
        String input = userInput.getText();
        String dukeMessage = this.duke.handleUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(dukeMessage, this.dukeImage)
        );
        userInput.clear();
    }
}
