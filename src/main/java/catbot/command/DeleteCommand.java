package catbot.command;

import catbot.TaskList;
import catbot.Ui;
import catbot.exception.InvalidTaskNumberException;
import catbot.task.Task;

/**
 * A class for the delete command.
 */
public class DeleteCommand extends Command {

    /** Index of the task to be deleted in string form. */
    private final String index;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param index Index of the task to be deleted in string form.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws InvalidTaskNumberException If the followup text after the command is not a valid integer.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        if (!isValidTaskNumber(taskList.size())) {
            throw new InvalidTaskNumberException("delete", this.index);
        }

        int i = Integer.parseInt(this.index) - 1;
        Task deleted = taskList.get(i);
        taskList.setUnDone(i);
        taskList.remove(i);
        this.response += "Oki! The following task is removed:)\n  " + deleted + "\n" + ui.printListCount();
    }

    /**
     * Checks if the task number is valid.
     *
     * @param taskListSize The size of the taskList to be checked against.
     * @return True if the task number is valid, false otherwise.
     */
    private boolean isValidTaskNumber(int taskListSize) {
        return !this.index.equals("") && isInteger(this.index) && (Integer.parseInt(this.index) - 1) >= 0
                && (Integer.parseInt(this.index) - 1) < taskListSize;
    }


    /**
     * Checks if the text is an integer.
     *
     * @param input The text to be checked.
     * @return The boolean representing if the text is an integer.
     */
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
