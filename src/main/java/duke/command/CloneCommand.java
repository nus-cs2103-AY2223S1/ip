package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulation of the command of cloning a task.
 *
 * @author Sun Ruoxin
 */
public class CloneCommand extends Command {
    /** The index of the task ot be cloned. */
    protected int index;

    /**
     * Class constructor.
     *
     * @param index the index of the task to be deleted
     */
    public CloneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * Creates a copy of the task.
     *
     * @param tasks   the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        Task taskToBeCloned = tasks.get(index);
        if (taskToBeCloned instanceof Todo) {
            Todo todoToBeCloned = (Todo) taskToBeCloned;
            Todo clone = new Todo(todoToBeCloned.getDescription(), todoToBeCloned.getStatusBoolean());
            tasks.add(clone);
        } else if (taskToBeCloned instanceof Deadline) {
            Deadline deadlineToBeCloned = (Deadline) taskToBeCloned;
            Deadline clone = new Deadline(deadlineToBeCloned.getDescription(),
                    deadlineToBeCloned.getStatusBoolean(), deadlineToBeCloned.getBy());
            tasks.add(clone);
        } else if (taskToBeCloned instanceof Event) {
            Event eventToBeCloned = (Event) taskToBeCloned;
            Event clone = new Event(eventToBeCloned.getDescription(),
                    eventToBeCloned.getStatusBoolean(), eventToBeCloned.getAt());
            tasks.add(clone);
        }
        return "Noted. I've cloned this task:\n"
                + "  " + taskToBeCloned + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
