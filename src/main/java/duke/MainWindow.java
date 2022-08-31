package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    // FXML OBJECTS
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // DUKE INSTANCE
    private Duke duke;

    // IMAGES
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image lurchImage = new Image(this.getClass().getResourceAsStream("/images/DaLurch.png"));

    public void setDuke(Duke d) {
        this.duke = d;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getLurchDialog(Ui.getGreetingMessage(), lurchImage)
        );
    }

    @FXML
    public void handleUserInput() {
        String userText = userInput.getText();
        String lurchText = duke.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getLurchDialog(lurchText, lurchImage)
        );
        userInput.clear();
    }
}
