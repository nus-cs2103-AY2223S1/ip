package duke.ui;

import duke.Duke;
import duke.Ui;
import duke.exception.DukeException;
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
    private Ui ui;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ui = new Ui();
        ImageView dukeImageView = new ImageView(dukeImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.getGreetMessage()), dukeImageView)
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
        if (userInput.getText().length() == 0) {
            return;
        }
        Label input = new Label(userInput.getText());
        Label response;
        String responseString = "";
        try {
            responseString = duke.getResponse(userInput.getText());
            response = new Label(responseString);
        } catch (DukeException e) {
            response = new Label(e.getMessage());
        }
        ImageView userImageView = new ImageView(userImage);
        ImageView dukeImageView = new ImageView(dukeImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImageView),
                DialogBox.getDukeDialog(response, dukeImageView)
        );
        userInput.clear();
        if (responseString == Ui.showExitMessage()) {
            Ui.exitProgram();
        }
    }
}