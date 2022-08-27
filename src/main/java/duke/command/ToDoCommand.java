package duke.command;

import duke.FileStorage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Command used to add a todo task into the taskList.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private Task task;
    public ToDoCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a todo task to the taskList, saves to file.
     *     and returns the corresponding message to the GUI.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        list.addTask(task);
        storage.writeToFile(list.getList());
        return String.format("Got it. I've added this task:\n  %s"
                + "\nNow you have %d tasks in the list.", task , list.getListSize());
    }
}
