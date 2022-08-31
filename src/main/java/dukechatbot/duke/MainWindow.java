package dukechatbot.duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The class that acts as the Anchor Pane to the many nodes implementing the application.
 */
public class MainWindow extends AnchorPane {
    /**
     * Instance of vertically aligned dialog box.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * The instance of scroll pane to allow client to scroll.
     */
    @FXML
    private ScrollPane sp;
    /**
     * The instance of text field to allow users to input their commands.
     */
    @FXML
    private TextField userInput;
    /**
     * The Button instance allows users an alternative way to submit their commands
     * to Duke.
     */
    @FXML
    private Button enterButton;
    /**
     * The image representing the user.
     */
    private Image userImg = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    /**
     * The image representing Duke.
     */
    private Image dukeImg = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    private Duke duke;

    /**
     * This allows the caller to set the Duke instance to the current run of the application.
     * @param dk the Duke instance to be set for the application run.
     */
    public void setDuke(Duke dk) {
        this.duke = dk;
    }

    /**
     * This will greet the user upon the initialisation of the JavaFX application.
     */
    @FXML
    public void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello from\n" + logo
                + "Hello I'm Duke, what can I do for you?", dukeImg));
    }

    /**
     * handles user inputs by calling on duke to process the inputs and outputting them as dialogs
     * @throws IOException when getResponse throws the exception.
     */
    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        String dukeResponse = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg), DialogBox.getDukeDialog(dukeResponse, dukeImg)
        );
        userInput.clear();
    }
}
