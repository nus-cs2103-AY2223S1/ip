package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for duke.ui.MainWindow. Provides the layout for the other controls.
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

    /**
     * Initialise the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greetMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        ImageView dukeImageView = new ImageView(dukeImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(greetMessage), dukeImageView)
        );
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
        Label input = new Label(userInput.getText());
        Label response = new Label(duke.getResponse(userInput.getText()));
        ImageView userImageView = new ImageView(userImage);
        ImageView dukeImageView = new ImageView(dukeImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImageView),
                DialogBox.getDukeDialog(response, dukeImageView)
        );
        userInput.clear();
    }
}