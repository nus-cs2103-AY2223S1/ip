package duke;

import java.util.concurrent.CompletableFuture;

import exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.Parser;
import utils.Ui;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaEnlistee.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Ditto.png"));


    /**
     * Initialises the chat box.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.welcomeUser(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the
     * user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";

        input = input.trim();

        if (input.equals("bye")) {
            response = Ui.sayGoodbye();
            CompletableFuture.runAsync(this::terminate);
        } else if (input.equals("list")) {
            response = duke.getTaskList().listTasks();
        } else {
            try {
                response = Parser.decide(input, input.split(" "), duke.getTaskList(), duke.getStorage());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        }
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private void terminate() {
        try {
            Thread.sleep(850);
        } catch (InterruptedException e) {
            System.out.println("Unable to set delay.");
        }
        System.exit(0);
    }
}
