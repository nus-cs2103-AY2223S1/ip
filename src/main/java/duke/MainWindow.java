package duke;


import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Main window class for running Duke.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;


    private Duke duke;

    private final InputStream userImagePath =
            this.getClass().getResourceAsStream("/images/user.jpg");
    private final InputStream falconImagePath =
            this.getClass().getResourceAsStream("/images/falcon.jpg");
    private Image userImage = new Image(userImagePath);
    private Image falconImage = new Image(falconImagePath);

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBoxUser.getUserDialog(input, userImage),
                DialogBoxFalcon.getFalconDialog(response, falconImage)
        );
        userInput.clear();
    }

}
