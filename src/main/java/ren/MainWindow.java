package ren;

import java.io.InputStream;

import javafx.application.Platform;
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

    /** The instance of Ren for this program. */
    private Ren ren;

    /** Profile picture for the user. */
    private Image userImage;

    /** Profile picture for Ren. */
    private Image renImage;

    /**
     * Initializes the GUI. Greets the user.
     */
    @FXML
    public void initialize() {
        // Credits for Image
        // https://pixabay.com/vectors/blank-profile-picture-mystery-man-973460/
        InputStream userImageLoader = this.getClass().getResourceAsStream("/images/user.png");
        // Credits for Image
        // https://www.freepik.com/free-photo/robot-with-clipboard_958202.htm#query=robot&position=9&from_view=author
        InputStream renImageLoader = this.getClass().getResourceAsStream("/images/ren.png");
        assert userImageLoader != null : "userImage in MainWindow should not be null";
        assert renImageLoader != null : "renImage in MainWindow should not be null";

        userImage = new Image(userImageLoader);
        renImage = new Image(renImageLoader);

        String greetings = " Greetings! My name is Ren ^_^\n How may I be of service today?\n";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getRenDialog(greetings, renImage)
        );
    }

    /**
     * Setter method for ren.
     *
     * @param ren Instance of Ren.
     */
    public void setRen(Ren ren) {
        this.ren = ren;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ren's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits the program if bye command entered.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response;
        try {
            response = ren.interpret(input);
        } catch (RenException e) {
            response = e.toString();
        }
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getRenDialog(response, renImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}
