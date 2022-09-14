package rattus.chatbot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rattus.chatbot.Rattus;

/**
 * The main anchor pane which contains all other JavaFX nodes.
 *
 * @author jq1836
 */
public class MainWindow extends AnchorPane {
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image rattusImage = new Image(this.getClass().getResourceAsStream("/images/DaRat.jpg"));

    private Rattus rattus;

    public void setDuke(Rattus rattus) {
        this.rattus = rattus;
        dialogContainer.getChildren().add(DialogBox.getRattusDialog(rattus.greet(), rattusImage));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, the first being the user's.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = rattus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRattusDialog(response, rattusImage)
        );
        userInput.clear();
    }
}
