package duke;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

/**
 * Main window class for running Duke.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    private Duke duke;

    private final InputStream USER_IMAGE_PATH =
            this.getClass().getResourceAsStream("/images/user.jpg");
    private final InputStream FALCON_IMAGE_PATH =
            this.getClass().getResourceAsStream("/images/falcon.jpg");
    private Image userImage = new Image(USER_IMAGE_PATH);
    private Image falconImage = new Image(FALCON_IMAGE_PATH);

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, falconImage)
        );
        userInput.clear();
    }

}
