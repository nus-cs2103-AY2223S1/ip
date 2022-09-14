package sally.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image sallyImg = new Image(this.getClass().getResourceAsStream("/images/sally.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the main window to current sally.
     *
     * @param sally sally that is initialized
     */
    public void setSally(Sally sally) {
        this.sally = sally;
        this.sally.getUi().setMainWindow(this);
    }

    /**
     * Handles command by creating the dialog boxes for both user and input, as well as clearing the input after it is processed.
     */
    @FXML
    private void handlesCommands() {
        String command = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command, user));
        sally.run(command);
        userInput.clear();
    }

    /**
     * Handles Sally's reply in MainWindow
     *
     * @param reply message that will be shown.
     */
    public void addSallyDialog(String reply) {
        dialogContainer.getChildren().add(DialogBox.getSallyDialog(reply, sallyImg));
    }
}
