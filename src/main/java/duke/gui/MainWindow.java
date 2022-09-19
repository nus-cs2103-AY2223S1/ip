package duke.gui;

import java.util.function.Supplier;

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

    private Supplier<Image> userImageGetter = () -> GuiDataController.getSingleton().getUserImage();
    private Supplier<Image> dukeImageGetter = () -> GuiDataController.getSingleton().getDukeImage();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Create a default Duke object with the default save path
     */
    public void makeDuke() {
        DukeIo io = new DukeGuiIo(txt -> {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(txt, dukeImageGetter.get()));
        }, txt -> {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(txt, dukeImageGetter.get(), true));
        });
        duke = Duke.createApplication(io);
        if (duke == null) {
            exitProgramAfterDelay(2000);
        }
    }

    /**
     * Create a Duke object using the specified save path
     *
     * @param filepath path to the save file/which file to save
     */
    public void makeDuke(String filepath) {
        DukeIo io = new DukeGuiIo(txt -> {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(txt, dukeImageGetter.get()));
        }, txt -> {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(txt, dukeImageGetter.get(), true));
        });
        duke = Duke.createApplication(io, filepath);
        if (duke == null) {
            exitProgramAfterDelay(5000);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = readAndDisplayUserInput();
        if (input.length() == 0) {
            return;
        }

        if (!duke.handleInput(input)) {
            exitProgramAfterDelay(2000);
        }
    }

    private String readAndDisplayUserInput() {
        String input = userInput.getText().trim();
        if (input.length() == 0) {
            return "";
        }

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImageGetter.get()));
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
                    System.out.println("Sleep is interrupted!");
                }
                return null;
            }
        };
    }

    /**
     * Exits the program after a set period of delay
     *
     * @param milisecond count down time before delay
     */
    private void exitProgramAfterDelay(int milisecond) {
        Task<Void> delayTask = makeDelayTask(milisecond);
        delayTask.setOnSucceeded(e -> {
            exitProgram();
        });

        new Thread(delayTask).start();
    }

    private void exitProgram() {
        Platform.exit();
    }
}
