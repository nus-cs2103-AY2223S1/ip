/**
 * Represents command to Duke to add a task to the list.
 *
 * @author WR3nd3
 */
public class AddCommand extends Command {

    private Task_Id id;
    private String content = "";
    private String time = "";

    /**
     * Constructs AddCommand for a task without a date tagged to it.
     *
     * @param id Type of task.
     * @param content String representing description of task.
     */
    public AddCommand(Task_Id id, String content) {
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
    public AddCommand(Task_Id id, String content, String time) {
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
