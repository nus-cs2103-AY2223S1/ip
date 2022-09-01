package duke.ui;

import java.nio.file.Path;
import java.util.function.Consumer;

import duke.Duke;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

//@@author RezwanArefin01-reused
//Adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Initializes the MainWindow.
     *
     * Performs the following tasks currently:
     * - Adds scrolling support for the {@code ScrollPane}.
     * - Moves the scroll bar to the end when a new child is added to the {@code VBox}.
     * - Disables the {@code Button} if the {@code TextField} is empty.
     */
    @FXML
    private void initialize() {
        scrollPane.setOnScroll(event -> {
            scrollPane.setVvalue(scrollPane.getVvalue() - event.getDeltaX() / dialogContainer.getHeight());
        });
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
        sendButton.disableProperty().bind(Bindings.isEmpty(userInput.textProperty()));
    }

    /**
     * Initializes the {@code Duke} used for the core logic.
     *
     * @param path The path to the data file.
     */
    protected void initDuke(Path path) {
        Consumer<String> printer = s -> {
            // JavaFX 11 doesn't support tab sizes, so we have to replace with spaces instead.
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(s.replaceAll("\t", "  "), dukeImage));
        };
        duke = new Duke(path, printer);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        userInput.clear();
        if (input.isEmpty()) {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        duke.execute(input);
    }
}
//@@author
