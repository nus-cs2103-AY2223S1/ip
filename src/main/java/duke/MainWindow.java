package duke;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String response = "";
        String input = "";
        String color = "#6adcd3";
        try {
            input = userInput.getText();
            response = duke.getResponse(input);
        } catch (DukeException e) {
            color = "#ffb6c1";
            response = e.getMessage();
        }
        DialogBox userGui = DialogBox.getUserDialog(input, userImage);
        userGui.setStyle("-fx-background-color:" + color + "; "
                + "maxHeight:-Infinity; maxWidth:-Infinity; prefWidth:388.0; spacing:15; -fx-background-radius: 16;");
        DialogBox dukeGui = DialogBox.getDukeDialog(response, dukeImage);
        dukeGui.setStyle("-fx-background-color:" + color + "; "
                + "maxHeight:-Infinity; maxWidth:-Infinity; prefWidth:388.0; spacing:15; -fx-background-radius: 16;");
        dialogContainer.getChildren().addAll(userGui, dukeGui);
        userInput.clear();
    }
}
