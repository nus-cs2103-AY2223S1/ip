package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.List;

import static duke.gui.DialogBox.getDukeDialogs;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DDuke.png"));

    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DUser.png"));

    public MainWindow() {}

    /**
     * Initializes MainWindow controller.
     */
    @javafx.fxml.FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.addDialogToContainer(getDukeDialogs(new Ui().printStartUpUi(), dukeImage));
    }

    /**
     * Sets duke attribute to duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Adds dialog boxes to dialogContainer attribute.
     */
    public void addDialogToContainer(List<DialogBox> dialogs) {
        for (DialogBox dialog : dialogs) {
            dialogContainer.getChildren().add(dialog);
        }
    }

    /**
     * Creates dialog boxes, one echoing user input and others containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        List<String> responses = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        this.addDialogToContainer(getDukeDialogs(responses, dukeImage));
        if (input.equals("yes") && duke.getExitStatus()) {
            System.exit(0);
            javafx.application.Platform.exit();
        }
        userInput.clear();
    }
}
