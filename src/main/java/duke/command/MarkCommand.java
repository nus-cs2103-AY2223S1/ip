package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Stores the index of the task to be marked when executed by MarkCommand
 */
public class MarkCommand extends Command {
    private final int indexToMark;

    /**
     * @param number 1 based indexing to remove from the task list
     */
    public MarkCommand(int number) {
        assert number >= 1 : "Number needs to be positive";
        this.indexToMark = number - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Task marked: " + tasks.mark(indexToMark);
    }

    /**
     * Does not terminate the App.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @param o Other object we are comparing with
     * @return whether each objects are of the same type
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof MarkCommand;
    }
}
