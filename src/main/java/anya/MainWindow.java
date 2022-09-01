package anya;

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

    private Anya anya;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserDefault.png"));
    private Image anyaImage = new Image(this.getClass().getResourceAsStream("/images/AnyaDefault.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAnya(Anya anya) {
        this.anya = anya;
        displayInitialMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = anya.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, anyaImage)
        );
        userInput.clear();
    }

    private void displayInitialMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(anya.getLoadFileStatus(), anyaImage));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(anya.greet(), anyaImage));
    }
}
