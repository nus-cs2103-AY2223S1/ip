package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Command to execute marking a task
 * @author Nephelite
 * @version 0.1
 */
public class MarkCommand extends Command {
    private String[] splitCommands;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a MarkCommand
     * @param splitCommands the commands split apart into an array
     * @param tasks TaskList Duke is using
     * @param ui Ui Duke is using
     * @since 0.1
     */
    public MarkCommand(String[] splitCommands, TaskList tasks, Ui ui) {
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
    public void execute() throws DukeException { //TODO: ui components
        if (splitCommands.length <= 1) {
            throw new DukeException("your duke.command is incomplete."
                    + "\nPlease use the [help] duke.command to check the proper usage of [mark].");
        } else if (splitCommands.length > 2) {
            throw new DukeException("your duke.command has too many arguments."
                    + "\nPlease use the [help] duke.command to check the proper usage of [mark].");
        } else if (isNumber(splitCommands[1])) {
            int taskId = Integer.parseInt(splitCommands[1]) - 1;
            if (tasks.size() <= taskId || taskId < 0) {
                throw new DukeException("that duke.task you want to mark does not exist. "
                        + "Use the [list] duke.command to check what tasks are available.");
            } else {
                tasks.getTask(taskId).setDone();
                ui.mark(tasks.getTask(taskId));
            }
        } else {
            throw new DukeException("your duke.command is incorrect."
                    + "\nPlease use the [help] duke.command to check the proper usage of [mark].");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
