package zupey.gui;

import zupey.Zupey;

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

    private Zupey zupey;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDino.png"));
    private Image zupeyImage = new Image(this.getClass().getResourceAsStream("/images/DaZupey.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setZupey(Zupey d) {
        zupey = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing zupey's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zupey.handleCommand(input);
        assert !response.equals("");
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox zupeyDialog = DialogBox.getzupeyDialog(response, zupeyImage);
        dialogContainer.getChildren().addAll(
                userDialog, zupeyDialog
        );
        userInput.clear();
    }
}
