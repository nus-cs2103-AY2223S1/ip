package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    @FXML
    private Button helpButton;
    @FXML
    private Button clearButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/joke4.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/joke.png"));

    /**
     * Sets the properties of main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        sendButton.setTextFill(Paint.valueOf("#FFFFFF"));

        sendButton.setBackground(new Background(new BackgroundFill(Color.web("#0b71f4"),
                new CornerRadii(5.0), new Insets(-5.0))));

        helpButton.setTextFill(Paint.valueOf("#FFFFFF"));

        helpButton.setBackground(new Background(new BackgroundFill(Color.web("#0b71f4"),
                new CornerRadii(5.0), new Insets(-5.0))));

        clearButton.setTextFill(Paint.valueOf("#FFFFFF"));

        clearButton.setBackground(new Background(new BackgroundFill(Color.web("#0b71f4"),
                new CornerRadii(5.0), new Insets(-5.0))));
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void help() {
        String commands = "Here are the list of commands:"
                + "\n • list"
                + "\n • done [index]"
                + "\n • undone [index]"
                + "\n • before [d/mm/yyyy]"
                + "\n • find [string]"
                + "\n • delete [index]"
                + "\n • todo [description]"
                + "\n • deadline [description] [d/mm/yyyy]"
                + "\n • event [description] [d/mm/yyyy]"
                + "\n • joke"
                + "\n • bye";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(commands, dukeImage)
        );
    }

    @FXML
    private void clear() {
        dialogContainer.getChildren().clear();
    }
}
