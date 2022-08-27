package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskId;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents command to duke.Duke to add a task to the list.
 *
 * @author WR3nd3
 */
public class AddCommand extends Command {

    private TaskId id;
    private String content = "";
    private String time = "";

    /**
     * Constructs command.AddCommand for a task without a date tagged to it.
     *
     * @param id Type of task.
     * @param content String representing description of task.
     */
    public AddCommand(TaskId id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Constructs command.AddCommand for a task with a date tagged to it.
     *
     * @param id Type of task.
     * @param content String representing description of task.
     * @param time String representing time of task.
     */
    public AddCommand(TaskId id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException {
        Task task;
        switch(id) {
        case T:
            task = new Todo(content, false);
            break;
        case E:
            task = new Event(content, time, false);
            break;
        case D:
            task = new Deadline(content, time, false);
            break;
        default:
            throw new DukeException();
        }
        tasks.addTask(task);
        storage.appendToList(task.summary());
        ui.showAdd(task, tasks.tasksLeft());
    }
}
