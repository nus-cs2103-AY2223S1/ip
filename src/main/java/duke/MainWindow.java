package duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pat.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private Image welcomeImage = new Image(this.getClass().getResourceAsStream("/images/welcome.png"));
    private Image unmarkImage = new Image(this.getClass().getResourceAsStream("/images/unmark.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets duke .
     * @param d the duke to be set
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Prints welcoming message.
     */
    public void printHello() {
        String welcomeMessage = "Hello! I'm Duke. \n What can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, welcomeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        Image dukeImage = determineImage(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private Image determineImage(String input) {
        String[] outputs = input.split(" ");
        if (outputs.length == 2 && outputs[0].equals("unmark")) {
            return unmarkImage;
        } return dukeImage;
    }
}