package catbot.command;

import catbot.TaskList;
import catbot.Ui;
import catbot.exception.InvalidTaskNumberException;

/**
 * A class for the mark and unmark commands.
 */
public class MarkUnmarkCommands extends Command {

    /** The type of command. */
    private final String type;

    /** Index of the task to be deleted in string form. */
    private final String index;

    /**
     * Constructor for the MarkUnmarkCommands class.
     *
     * @param type  The type of command.
     * @param index Index of the task to be deleted in string form.
     */
    public MarkUnmarkCommands(String type, String index) {
        this.type = type;
        this.index = index;
    }

    /**
     * Executes the mark"/"unmark commands.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws InvalidTaskNumberException If the followup text after the command is not a valid integer.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        if (!isValidTaskNumber(taskList.size())) {
            throw new InvalidTaskNumberException(this.type, this.index);
        }

        boolean wasChanged;
        int i = Integer.parseInt(this.index) - 1;
        switch (type) {
        case "mark":
            wasChanged = taskList.setDone(i);
            if (wasChanged) {
                this.response += "Nice! Task " + (i + 1) + " done!\n  " + taskList.get(i);
            } else {
                this.response += "Task " + (i + 1) + " is already done!\n  " + taskList.get(i);
            }
            break;
        case "unmark":
            wasChanged = taskList.setUnDone(i);
            if (wasChanged) {
                this.response += "Ok! Task " + (i + 1) + " marked as not done!\n  " + taskList.get(i);
            } else {
                this.response += "Task " + (i + 1) + " is already not done!\n  " + taskList.get(i);
            }
            break;
        default:
        }
        this.response += "\n" + ui.printListCount();
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
