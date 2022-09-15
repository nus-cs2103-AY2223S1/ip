package skylark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skylark.command.CommandList;
import skylark.skylark.Skylark;

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

    private Skylark skylark;
    private Stage stage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image skylarkImage = new Image(this.getClass().getResourceAsStream("/images/Skylark.png"));

    /**
     * Initialises the MainWindow by setting the scroll pane with the height of the container.
     * Displays a Hello message to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSkylarkDialog(Skylark.TEXT_HELLO, skylarkImage)
        );
    }

    public void setSkylark(Skylark skylark) {
        this.skylark = skylark;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals(CommandList.COMMAND_BYE.toString())) {
            this.stage.close();
        }

        String response = skylark.getResponse(input);
        assert response.length() > 0 : "Response should not be empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSkylarkDialog(response, skylarkImage)
        );
        userInput.clear();
    }
}
