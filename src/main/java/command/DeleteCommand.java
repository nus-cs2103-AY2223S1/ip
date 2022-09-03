package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>DeleteCommand class</h1>
 * Class that deletes the Task at the input index
 */
public class DeleteCommand extends Command {
    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Eh, you got that task number meh?";
    private static final String INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE = "Eh, you enter your task number "
            + "correctly anot?";
    /**
     * Creates the DeleteCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public DeleteCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Deletes the Task at the input index
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException Throws an error message indicating that the user's input is invalid
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String taskIndexToDelete = input.substring(6).trim();
        try {
            int taskIndex = Integer.parseInt(taskIndexToDelete);
            if (!hasTaskIndex(taskIndex)) {
                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
            }
            ui.printDeletedTaskMessage(tasks.get(taskIndex - 1), dialogContainer, userDialog);
            tasks.remove(taskIndex - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean hasTaskIndex(int taskIndex) {
        return taskIndex - 1 < this.tasks.getSize() && taskIndex - 1 >= 0;
    }
}
