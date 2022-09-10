package command;

import exception.DukeException;
import javafx.scene.layout.VBox;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>UnmarkCommand class</h1>
 * Class that unmarks the Task as not done at an input index
 */
public class UnmarkCommand extends Command {
    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Eh, you got that task number meh?";
    private static final String TASK_ALREADY_UNMARKED_ERROR_MESSAGE = "Eh, your task alr not done lah";
    private static final String INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE = "Eh, you enter your task number "
            + "correctly anot?";

    /**
     * Creates the UnmarkCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public UnmarkCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Unmarks the Task as not done at an input index
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException the exception to be thrown when Duke encounters an issue.
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String markIndexString = input.substring(6).trim();
        try {
            int taskIndex = Integer.parseInt(markIndexString);
            if (!hasTaskIndex(taskIndex)) {
                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
            }
            if (!this.tasks.get(taskIndex - 1).canChangeIsDone(false)) {
                throw new DukeException(TASK_ALREADY_UNMARKED_ERROR_MESSAGE);
            }
            this.tasks.get(taskIndex - 1).changeIsDone(false);
            assert !this.tasks.get(taskIndex - 1).getIsDone();
            ui.printUnmarkedMessage(this.tasks.get(taskIndex - 1), dialogContainer, userDialog);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean hasTaskIndex(int taskIndex) {
        return taskIndex - 1 < this.tasks.getSize() && taskIndex - 1 >= 0;
    }
}
