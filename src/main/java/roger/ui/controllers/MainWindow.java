package roger.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import roger.Roger;
import roger.ui.Response;

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

    private Roger roger;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rogerImage = new Image(this.getClass().getResourceAsStream("/images/Roger.png"));

    /**
     * Initialize the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRogerClass(Roger r) {
        roger = r;
    }

    /**
     * Greet the user.
     */
    @FXML
    public void greet() {
        Response greeting = roger.getGreeting();
        dialogContainer.getChildren().addAll(DialogBox.getRogerDialog(greeting.getMessage(), rogerImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = roger.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRogerDialog(response.getMessage(), rogerImage)
        );
        userInput.clear();

        if (response.isExit()) {
            roger.saveTasksToDatabase();
            Platform.exit();
        }
    }
}
