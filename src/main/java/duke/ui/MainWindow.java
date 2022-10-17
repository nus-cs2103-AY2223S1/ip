package duke.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import duke.Duke;
import duke.dukeexception.DukeException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stackPane.setPrefSize(anchorPane.getPrefWidth(), anchorPane.getPrefHeight()); //didn't work
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Takes created Duke class, initialises, and then output the greeting msg.
     * @param d created Duke class.
     * @throws DukeException
     * @throws IOException
     */
    public void setDuke(Duke d) throws DukeException, IOException {
        duke = d;
        String response = duke.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() throws DukeException, IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
