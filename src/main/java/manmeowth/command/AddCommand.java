package manmeowth.command;

import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.task.Deadline;
import manmeowth.task.Event;
import manmeowth.task.Task;
import manmeowth.task.TaskId;
import manmeowth.task.Todo;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to add a task to the list.
 *
 * @author WR3nd3
 */
public class AddCommand extends Command {

    private final TaskId id;
    private String content;
    private String time = "";

    /**
     * Constructs AddCommand for a task without a date tagged to it.
     *
     * @param id Type of task.
     * @param content String representing description of task.
     */
    public AddCommand(TaskId id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Constructs AddCommand for a task with a date tagged to it.
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
    public String execute(TaskList tasks, Ui ui, ListLoader storage) throws ManMeowthException {
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
            throw new ManMeowthException(ui.showHelp());
        }
        tasks.addTask(task);
        storage.appendToList(task.summary());
        return ui.showAdd(task, tasks.tasksLeft());
    }
}
