package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>EmptyCommand class</h1>
 * Class that throws an error message indicating that the user's input is empty
 */
public class EmptyCommand extends Command {
    private static final String EMPTY_USER_INPUT_ERROR_MESSAGE = "Eh you never type anything leh?";
    private static final String EMPTY_STRING = "";

    /**
     * Creates the EmptyCommand
     *
     * @param tasks the list of Tasks.
     * @param ui the Ui object that handles the User Interface.
     */
    public EmptyCommand(TaskList tasks, Ui ui) {
        super(tasks, EMPTY_STRING, ui);
    }

    /**
     * Throws an error message indicating that the user's input is empty
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException the exception to be thrown when Duke encounters an issue.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        throw new DukeException(EMPTY_USER_INPUT_ERROR_MESSAGE);
    }
}
