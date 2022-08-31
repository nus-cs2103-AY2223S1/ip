package carbon.gui;

import carbon.Carbon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
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

    private Carbon carbon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/clown.png"));
    private Image carbonImage = new Image(this.getClass().getResourceAsStream("/images/carbon.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Connects the Carbon class bot to the main window for handling business logic.
     *
     * @param carbon The Carbon object.
     */
    public void setCarbon(Carbon carbon) {
        this.carbon = carbon;
        String greeting = this.carbon.greet();
        this.dialogContainer.getChildren().add(DialogBox.getCarbonDialog(greeting, carbonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = carbon.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCarbonDialog(response, carbonImage)
        );
        this.userInput.clear();
    }
}
