package application;

import component.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mew.Mew;

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

    private Mew mew;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image mewImage = new Image(this.getClass().getResourceAsStream("/images/Mew.png"));

    /**
     * Initializes a dialog with an opening statement from Mew.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getMewDialog(Ui.showOpeningStatement(), mewImage)
        );
    }

    public void setMew(Mew m) {
        mew = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mew's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mew.processResponse(input);
        if (mew.isEndInput(input)) {
            userInput.clear();
            dialogContainer.getChildren().addAll(DialogBox.getMewDialog(response, mewImage));
            mew.endApplication();
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMewDialog(response, mewImage)
        );
        userInput.clear();
    }
}