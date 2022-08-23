package duke.commands;

import duke.utils.Storage;
import duke.tasks.Task;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Adds a new task to the task list and prints a confirmation message.
 *
 * @author sikai00
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
