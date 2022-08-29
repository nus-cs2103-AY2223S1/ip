package duke.command;

import duke.FileStorage;
import duke.task.TaskList;


/**
 * Command used to mark a particular task in the taskList as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks the task from the taskList as completed, saves to file
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        list.markTaskDone(index);
        storage.writeToFile(list.getList());
        return String.format("Nice! I've marked this task as done:\n  %s", list.retrieveTask(index));
    }
}
