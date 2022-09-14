package ploopy.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ploopy.Ploopy;

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

    private Ploopy ploopy;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image PLOOPY_IMAGE = new Image(this.getClass().getResourceAsStream("/images/Ploopy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPloopy(Ploopy p) {
        ploopy = p;
        dialogContainer.getChildren().addAll(
                DialogBox.getPloopyDialog(p.start(), PLOOPY_IMAGE)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ploopy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getPloopyDialog(response, PLOOPY_IMAGE)
        );
        userInput.clear();
    }
}
