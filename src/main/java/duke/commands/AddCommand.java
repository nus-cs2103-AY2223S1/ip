package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command that adds a Task to the specified TaskList.
 */
public class AddCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "add";
    private final Task task;

    /**
     * Initializes a new AddCommand instance.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);

        int size = taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msgBegin = "Got it. I've added this task:\n";
        String msgEnd = "\nNow you have " + size + " " + taskString + " in this list.";
        String msg = msgBegin + "  " + task + msgEnd;
        ui.prettyPrint(msg);

        Storage.appendTaskToStorage(task);
    }
}
