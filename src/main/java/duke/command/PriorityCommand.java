package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.Priority;
import duke.task.TaskList;

/**
 * Command used to set a task's priority.
 */
public class PriorityCommand extends Command {
    public static final String COMMAND_WORD = "priority";
    public static final String HELP_MESSAGE = "Set Priority: priority [index] [value]\n\n";

    private Priority priority;
    private int index;

    /**
     * Constructor for a priorityCommand
     *
     * @param index The given index.
     * @param priority The given priority.
     */
    public PriorityCommand(int index, Priority priority) {
        this.index = index - 1;
        this.priority = priority;
    }
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        list.setTaskPriority(index, priority);
        storage.writeToFile(list.getList());
        return ui.getPrioritySetMessage(list.retrieveTask(index));
    }
}
