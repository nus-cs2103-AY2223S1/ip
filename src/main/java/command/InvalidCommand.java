package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>InvalidCommand class</h1>
 * Class that throws an error message indicating
 * that the user's input is invalid
 */
public class InvalidCommand extends Command {
    private static final String DEFAULT_ERROR_MESSAGE = "What talking you";

    /**
     * Creates the InvalidCommand
     *
     * @param tasks the list of Tasks.
     * @param ui the Ui object that handles the User Interface.
     */
    public InvalidCommand(TaskList tasks, Ui ui) {
        super(tasks, "", ui);
    }

    /**
     * Throws an error message indicating that the user's input is invalid
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException the exception to be thrown when Duke encounters an issue.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        throw new DukeException(DEFAULT_ERROR_MESSAGE);
    }
}
