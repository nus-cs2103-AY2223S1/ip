package duke.gui;

import duke.inputoutput.DukeGuiIo;
import duke.inputoutput.DukeIo;
import duke.main.Duke;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Create a default Duke object with the default save path
     */
    public void makeDuke() {
        DukeIo io = new DukeGuiIo(dialogContainer, txt -> DialogBox.getDukeDialog(txt, dukeImage));
        duke = Duke.createApplication(io);
    }

    /**
     * Create a Duke object using the specified save path
     * 
     * @param filepath path to the save file/which file to save
     */
    public void makeDuke(String filepath) {
        DukeIo io = new DukeGuiIo(dialogContainer, txt -> DialogBox.getDukeDialog(txt, dukeImage));
        duke = Duke.createApplication(io, filepath);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = readAndDisplayUserInput();

        if (!duke.handleInput(input)) {
            exitProgramAfterTask(makeDelayTask(3000));
        }
    }

    private String readAndDisplayUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        return input;
    }

    private static Task<Void> makeDelayTask(int timeMs) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(timeMs);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
    }

    private <T> void exitProgramAfterTask(Task<T> task) {
        task.setOnSucceeded(e -> {
            exitProgram();
        });

        new Thread(task).start();
    }

    private void exitProgram() {
        Platform.exit();
        System.exit(0);
    }
}
