package chatbot.gui;

import chatbot.Zlimez;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Zlimez zlimez;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image zlimezImage = new Image(this.getClass().getResourceAsStream("/images/Reg.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//        String greet = zlimez.start();
//        dialogContainer.getChildren().addAll(
//                DialogBox.getZlimezDialog(greet, zlimezImage)
//        );
    }

    public void setZlimez(Zlimez d) {
        zlimez = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zlimez.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZlimezDialog(response, zlimezImage)
        );
        userInput.clear();
    }
}
