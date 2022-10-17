package duke.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author clarence-chew-reused
// Reused from this tutorial
// https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications at most

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * GUI writes to here.
     */
    private static final PipedWriter writer = new PipedWriter();

    private static final BufferedWriter bufferedWriter = new BufferedWriter(writer);
    /**
     * GUI reads from here.
     */
    private static BufferedReader reader;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/userImage.png"),
            96, 96, false, true);
    private final Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/dukeImage.png"),
            96, 96, false, true);

    /**
     * Get the writer that writes out GUI interactions.
     *
     * @return The writer that writes out GUI interactions.
     */
    public static PipedWriter getWriter() {
        return writer;
    }

    @FXML
    private void initialize() {
        try {
            reader = new BufferedReader(new PipedReader(GraphicUi.getWriter()));
        } catch (IOException e) {
            System.out.println("Cannot initialize GUI output reader - terminating");
            throw new RuntimeException(e);
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        try {
            bufferedWriter.write(input);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException ex) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(String.format(
                            "I did not manage to send that to application logic:\n%s",
                            input), dukeImage)
            );
        }
        getOutput();
    }

    /**
     * Gets output. Used for first message too.
     */
    public void getOutput() {
        try {
            StringBuilder result = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line.length() == 0) {
                    break;
                }
                result.append(line);
                result.append('\n');
            }
            String responseWithoutTrailingNewline = result.substring(0, result.length() - 1);
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(responseWithoutTrailingNewline, dukeImage)
            );
        } catch (IOException ex) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("I did not manage to read it...", dukeImage)
            );
        }
    }
}

//@@author
