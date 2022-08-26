package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Mark a task as undone when command is called.
 */
public class UndoneCommand extends Command {
    private int taskNum;

    /**
     * Marks task as undone based on index entered when command is called.
     *
     * @param taskNum index of task to be marked as undone
     */
    public UndoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum >= tasks.size()) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }

        tasks.markTaskAsUndone(taskNum - 1);
        ui.printMessage("\tNice! I've marked this task as done:\n\t" +
                tasks.get(taskNum - 1).toString());
        storage.save(tasks.getAllTasks());
    }
}
