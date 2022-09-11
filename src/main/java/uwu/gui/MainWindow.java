package uwu.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uwu.uwu.UwuBot;

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

    private UwuBot uwuBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UwUser.png"));
    private Image uwuImage = new Image(this.getClass().getResourceAsStream("/images/Uwu.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getUwuDialog("hellu!\ni am oo woo <:"
                + "\nhow can i be of service today?", uwuImage));

        assert this.scrollPane != null : "[scrollPane] FXML was improperly configured.";
        assert this.dialogContainer != null : "[dialogContainer] FXML was improperly configured.";
        assert this.userInput != null : "[userInput] FXML was improperly configured.";
        assert this.sendButton != null : "[sendButton] FXML was improperly configured.";
    }

    public void setUwu(UwuBot uwu) {
        uwuBot = uwu;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uwuBot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUwuDialog(response, uwuImage)
        );

        userInput.clear();

        if (uwuBot.isEnd(input)) {
            sendButton.setDisable(true);
            userInput.setDisable(true);
        }
    }
}
