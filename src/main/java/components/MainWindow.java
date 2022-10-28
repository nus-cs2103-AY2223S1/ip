package components;

import exceptions.HenryException;
import henry.Henry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String GREETING = "Hello! I am Henry! How may I assist you today?";

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
            HenryDialogBox.getHenryDialog(GREETING)
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
                UserDialogBox.getUserDialog(input),
                HenryDialogBox.getHenryDialog(response)
            );
        } catch (HenryException e) {
            dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                ErrorDialogBox.getErrorDialog(e.getMessage())
            );
        }
        userInput.clear();
    }
}
