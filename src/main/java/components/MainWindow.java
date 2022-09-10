package components;

import java.util.Objects;

import exceptions.HenryException;
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

    private static final String GREETING = "HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?";
    private final Image userImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image henryImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/henry.png")));

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

    /**
     * Initializes the main window of the JavaFX application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            HenryDialogBox.getHenryDialog(GREETING, henryImage)
        );
    }

    /**
     * Sets the Henry object to be used in the GUI.
     *
     * @param henry the Henry object to be used for operations
     */
    public void setHenry(Henry henry) {
        this.henry = henry;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = henry.getResponse(input);
            dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                HenryDialogBox.getHenryDialog(response, henryImage)
            );
        } catch (HenryException e) {
            dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                HenryDialogBox.getHenryDialog(e.getMessage(), henryImage)
            );
        }
        userInput.clear();
    }
}
