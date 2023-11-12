package duke.components;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the Layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private HBox header;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rosie.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Lisa.jpg"));
    private Image pinkVenom = new Image(this.getClass().getResourceAsStream("/images/Pink_Venom.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Lily" + Emoji.VICTORY_HAND + Emoji.VICTORY_HAND
                        + Emoji.CONFETTI_BALL + "\n"
                        + "What can I do for you?\n"
                        + Emoji.CHECK_MARK + "A list of options can be found with 'help' command", dukeImage)
        );
        header.getChildren().add(Header.setHeader("Lily", dukeImage, pinkVenom));
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
