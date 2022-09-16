package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Marks the {@link Task} in the {@link TaskList} as done
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Initializes the MarkCommand to mark a task
     * @param index Index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            Task t = tasks.getTask(index);
            t.markAsDone();
            System.out.println(tasks.getTask(index));
            storage.rewrite(tasks);
            return ui.showTaskMarkMessage(t);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
