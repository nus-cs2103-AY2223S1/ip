package duke.ui;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.PNG"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.PNG"));

    /**
     * Creates initial layout and response of duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new BotUI().sayHello(), dukeImage));
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(BotUI.userSpeak() + input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        //@@author jhchee18-reused
        //Adopted idea of handle bye command
        //https://github.com/jhchee18/ip/blob/master/src/main/java/duke/frontend/MainWindow.java
        if (response.contains("Goodbye")) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
        }
        //@@author
        userInput.clear();
    }
}
