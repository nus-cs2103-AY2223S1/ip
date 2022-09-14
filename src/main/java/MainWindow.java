import Duke.WagwanException;
import Duke.WagwanUi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;

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

    private Wagwan wagwan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mike.jpeg"));
    private Image wagwanImage = new Image(this.getClass().getResourceAsStream("/images/KSI.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = WagwanUi.welcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, wagwanImage)
        );
    }

    public void setWagwan(Wagwan w) {
        wagwan = w;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws WagwanException, IOException {
        String input = userInput.getText();
        String response = wagwan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, wagwanImage)
        );
        userInput.clear();
    }
}