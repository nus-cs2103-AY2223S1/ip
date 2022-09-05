package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Stores the index of the task to be marked when executed by MarkCommand.
 */
public class MarkCommand extends Command {
    private int indexToMark;

    /**
     * @param number 1 based indexing for the Task to be marked.
     */
    public MarkCommand(int number) {
        assert number >= 1 : "Number needs to be positive";
        this.indexToMark = number - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
}
