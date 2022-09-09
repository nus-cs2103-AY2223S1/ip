package command;

import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>ExitCommand class</h1>
 * Class that prints out the exit message in the Ui and exits the application
 */
public class ExitCommand extends Command {

    private static final double EXIT_DURATION = 2.0;

    /**
     * Creates the ExitCommand
     *
     * @param tasks the list of Tasks.
     * @param ui the Ui object that handles the User Interface.
     */
    public ExitCommand(TaskList tasks, Ui ui) {
        super(tasks, "", ui);
    }

    /**
     * Prints out the exit message in the Ui and exits the application
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) {
        try {
            storage.writeToFile(tasks);
            ui.sayGoodbye(dialogContainer, userDialog);
            executeDelay();
        } catch (DukeException e) {
            ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
        }
    }

    private void executeDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(EXIT_DURATION));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
