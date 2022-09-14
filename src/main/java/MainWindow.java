import Duke.Duke;

import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.TimerTask;
import java.util.Timer;

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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Girl.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Boy.png"));

    private String greetings   = "Hello! I am Duke.\n" + "Nice to Meet you, how can I help you ?";

    private String hint        = "You can type \"help\" to see all my services and get more information : )";
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings, dukeImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(hint, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. TextField and Button are onAction #handleUserInput
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText().trim();
        String response = duke.getResponse(input);
        userInput.clear();

        if (input.equals("help")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help Page");
            alert.setHeaderText("This is the help list:");
            alert.setContentText(duke.getHelp());
            alert.showAndWait();

        } else if (input.equals("list")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("List");
            alert.setHeaderText("The current list:");
            alert.setContentText(response);
            alert.showAndWait();

        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

            if (input.trim().equals("bye")) {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 1000L);
            }
        }
    }
}