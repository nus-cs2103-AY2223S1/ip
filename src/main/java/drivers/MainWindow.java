package drivers;

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

    private Tumu tumu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sadcat.png"));
    private Image tumuImage = new Image(this.getClass().getResourceAsStream("/images/bongocat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//        dialogContainer.getChildren().add(
//                DialogBox.getTumuDialog(tumu.runGreeting(), tumuImage)
//        );
    }

    public void setTumu(Tumu d) {
        tumu = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tumu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tumu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTumuDialog(response, tumuImage)
        );
        userInput.clear();
    }
}
