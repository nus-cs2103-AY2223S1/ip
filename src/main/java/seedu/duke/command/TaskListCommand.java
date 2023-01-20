package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

abstract class TaskListCommand extends Command {
    private final String cmd;

    TaskListCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * This method will handle operations that involve manipulating
     * the TaskList.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     * @throws DukeException    Thrown when encountering invalid inputs/index
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] split = cmd.split(" ");
        if (split.length == 2) {
            try {
                int taskIndex = Integer.parseInt(split[1]);
                if (taskIndex > tasks.numOfTasks() || taskIndex < 1) {
                    throw new DukeException(ui.getTaskListIndexErrorMessage());
                }
                specialisedFunction(tasks,ui, storage, taskIndex);
                storage.updateSave(tasks);
            } catch (NumberFormatException e) {
                throw new DukeException(ui.getInvalidInputMessage());
            }
        } else if (split.length == 1) {
            throw new DukeException(ui.getNoIndexProvidedErrorMessage());
        } else {
            throw new DukeException(ui.getInvalidInputMessage());
        }
    }

    abstract void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex);
}
