package sally.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


/**
 * MainWindow class to control the main window of GUI.
 *
 * @author liviamil
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

    private Sally sally;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image sallyImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/sally.png")));

    @FXML
    public void initialize() {
        addSallyDialog("Hello! I'm Sally\nWhat can I do for you?");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the main window to current sally.
     *
     * @param s sally that is initialized
     */
    public void setSally(Sally s) {
        sally = s;
        sally.getUi().setMainWindow(this);
    }

    /**
     * Handles command by creating the dialog boxes for both user and input, as well as clearing the input after it is processed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        sally.run(input);
        userInput.clear();
    }

    /**
     * Handles Sally's reply in MainWindow
     *
     * @param reply message that will be shown.
     */
    public void addSallyDialog(String reply) {
        dialogContainer.getChildren().add(DialogBox.getSallyDialog(reply, sallyImage));
    }
}
