package puke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Stage stage;
    private Duke duke;

    protected static MainWindow mw = new MainWindow();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showIntro();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert !input.isEmpty() : "Input cannot be empty!";
        assert !response.isEmpty() : "Puke doesn't feel like talking to you, try again later";
        Label inputLabel = new Label("You say: " + input);
        Label responseLabel = new Label(response);

        if (response.equals("Puke says: Bye")) {
            stage.close();
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(inputLabel, new ImageView(userImage)),
                DialogBox.getPukeDialog(responseLabel, new ImageView(dukeImage))
        );
        userInput.clear();
    }

    public void showIntro() {
        Label introLabel = new Label(Ui.intro());
        dialogContainer.getChildren().addAll(
                DialogBox.getPukeDialog(introLabel, new ImageView(dukeImage))
        );
    }

    public void setStage(Stage s) {
        this.stage = s;
    }
}

