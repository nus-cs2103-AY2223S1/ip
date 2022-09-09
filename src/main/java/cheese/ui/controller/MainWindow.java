package cheese.ui.controller;

import cheese.Cheese;
import cheese.ui.Response;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controls MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    /** Scroll pane to contain the chat. */
    @FXML
    private ScrollPane scrollPane;

    /** Container for dialog box. */
    @FXML
    private VBox dialogContainer;

    /** Text field for user input. */
    @FXML
    private TextField userInput;

    /** An instance of <code>Cheese</code>. */
    private Cheese cheese;

    /** Image to represent the user. */
    private final Image IMAGE_USER =
            new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /** Image to represent Cheese. */
    private final Image IMAGE_CHEESE =
            new Image(this.getClass().getResourceAsStream("/images/Cheese.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren()
                .add(DialogBox.getCheeseDialog(Response.getWelcomeMessage(), IMAGE_CHEESE));
    }

    /**
     * Sets an instance of <code>Cheese</code> to this controller.
     *
     * @param c An instance of <code>Cheese</code>.
     */
    public void setCheese(Cheese c) {
        cheese = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cheese.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, IMAGE_USER),
                DialogBox.getCheeseDialog(response, IMAGE_CHEESE));
        userInput.clear();
    }
}
