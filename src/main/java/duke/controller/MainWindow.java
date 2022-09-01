package duke.controller;

import duke.DukeApplication;

import duke.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String HORIZONTAL_LINE = "______________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
          + "|  _ \\ _   _| | _____ \n"
          + "| | | | | | | |/ / _ \\\n"
          + "| |_| | |_| |   <  __/\n"
          + "|____/ \\__,_|_|\\_\\___|\n";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeApplication duke;

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg()));
    }

    public void setDuke(DukeApplication d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        String response = this.duke.process(input);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response));
        userInput.clear();
    }

    private String welcomeMsg() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HORIZONTAL_LINE);
        stringBuilder.append("\n");
        // stringBuilder.append(LOGO);
        // stringBuilder.append("\n");
        stringBuilder.append("How may I help you?\n");
        stringBuilder.append(HORIZONTAL_LINE);
        return stringBuilder.toString();
    }
}
