package duke.command;

import duke.FileStorage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Command used to add a deadline task into the taskList.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task task;
    public DeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a deadline task to the taskList, saves to file.
     *     and returns the corresponding message to the user.
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
