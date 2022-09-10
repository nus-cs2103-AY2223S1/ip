package skyler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Skyler skyler;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image skylerImage = new Image(this.getClass().getResourceAsStream("/images/Skyler.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getSkylerDialog(Ui.GREETING, skylerImage));
    }

    public void setSkyler(Skyler skyler) {
        this.skyler = skyler;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Skyler's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        closeStageIfByeInput(input, this.stage);

        String response = skyler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkylerDialog(response, skylerImage)
        );

        userInput.clear();
    }

    private void closeStageIfByeInput(String input, Stage stage) {
        if (input.equals("bye")) {
            stage.close();
        }
    }
}
