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

    /** Image to represent the user. */
    private Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /** Image to represent Cheese. */
    private Image cheeseImage =
            new Image(this.getClass().getResourceAsStream("/images/Cheese.png"));

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

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren()
                .add(DialogBox.getCheeseDialog(Response.getWelcomeMessage(), cheeseImage));
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
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getCheeseDialog(response, cheeseImage));
        userInput.clear();
    }
}
