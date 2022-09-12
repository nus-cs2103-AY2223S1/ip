package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    public MainWindow() {}


    @javafx.fxml.FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.addDialogToContainer(getDukeDialogs(new Ui().printStartUpUi(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void addDialogToContainer(List<DialogBox> dialogs) {
        for (DialogBox dialog : dialogs) {
            dialogContainer.getChildren().add(dialog);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        List<String> responses = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        this.addDialogToContainer(getDukeDialogs(responses, dukeImage));
        userInput.clear();
        if (responses.get(0).equals("Chatbot stopped, all previous tasks will be auto-saved :D")) {
            System.exit(0);
            javafx.application.Platform.exit();
        }
    }
}
