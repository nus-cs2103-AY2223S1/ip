package duke.command;

import duke.InvalidIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * PriorityCommand changes priority of a task.
 */
public class PriorityCommand extends Command {
    private int indexToChangePriority;
    private String priority;

    /**
     * Constructor for PriorityCommand
     *
     * @param indexToChangePriority index of task to delete.
     */
    public PriorityCommand(int indexToChangePriority, String priority) {
        super();
        this.indexToChangePriority = indexToChangePriority;
        this.priority = priority;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        boolean isSmallerThanZero = indexToChangePriority < 0;
        boolean isLargerThanTaskListSize = indexToChangePriority > tasks.getSize();
        if (isSmallerThanZero || isLargerThanTaskListSize) {
            throw new InvalidIndexException();
        }

        tasks.getTask(indexToChangePriority).setPriority(priority);
        storage.save(tasks.getTasks());

        return String.format("Nice! I've changed the priority of this task:\n"
                + "%s", tasks.getTask(indexToChangePriority).toString());
    }
}
