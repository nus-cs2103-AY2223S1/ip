package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Command to execute deleting a task
 * @author Nephelite
 * @version 0.1
 */
public class DeleteCommand extends Command {
    private String[] splitCommands;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a DeleteCommand
     * @param splitCommands the commands split apart into an array
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public DeleteCommand(String[] splitCommands, TaskList tasks, Ui ui) {
        this.splitCommands = splitCommands;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Checks if a String is a number
     * @param string
     * @return true if string is a number. Otherwise, false
     * @since 0.1
     */
    private boolean isNumber(String string) {
        char[] numberArray = string.toCharArray();
        for (char c : numberArray) {
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        if (splitCommands.length == 1) {
            throw new DukeException("your duke.command is incomplete."
                    + "\nPlease use the [help] duke.command to check the proper usage of [delete].");
        } else if (splitCommands.length > 2) {
            throw new DukeException("your duke.command has too many arguments."
                    + "\nPlease use the [help] duke.command to check the proper usage of [delete].");
        } else if (isNumber(splitCommands[1])) {
            int taskId = Integer.parseInt(splitCommands[1]) - 1;
            if (tasks.size() <= taskId || taskId < 0) {
                throw new DukeException("that duke.task you want to delete does not exist."
                        + "\nUse the [list] duke.command to check what tasks are available.");
            } else {
                ui.delete(tasks.getTask(taskId), (tasks.size() - 1));
                tasks.remove(taskId);
                storage.saveDuke(tasks);
            }
        } else {
            throw new DukeException("your duke.command is incorrect."
                    + "\nPlease use the [help] duke.command to check the proper usage of [delete].");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
