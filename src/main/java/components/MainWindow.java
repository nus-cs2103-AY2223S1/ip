package components;

import java.util.Objects;

import henry.Henry;
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
    private final Image userImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image henryImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/henry.png")));
    private static final String GREETING = "HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?";

    // FXML
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Henry henry;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getHenryDialog(GREETING, henryImage)
        );
    }

    public void setHenry(Henry h) {
        henry = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = henry.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getHenryDialog(response, henryImage)
        );
        userInput.clear();
    }
}
