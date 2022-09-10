package mort.command;

import mort.exception.MortException;
import mort.parser.Parser;
import mort.storage.Storage;
import mort.task.Deadline;
import mort.task.Event;
import mort.task.Task;
import mort.task.TaskList;
import mort.task.TaskType;
import mort.task.ToDo;
import mort.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    /** Type of task to be added */
    private TaskType taskType;
    /** Description of the task */
    private String description;
    /** Date of the task */
    private String dateString;

    /**
     * Constructor to initialize the add command with the task type and description.
     * @param taskType The task type.
     * @param description The task description.
     */
    public AddCommand(TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        assert !this.description.isBlank() : "desc should not be blank";
    }

    /**
     * Constructor to initialise the add command with the task type, description and date.
     * @param taskType The task type.
     * @param description The task description.
     * @param dateString The task date.
     */
    public AddCommand(TaskType taskType, String description, String dateString) {
        this.taskType = taskType;
        this.description = description;
        this.dateString = dateString;
        assert !this.description.isBlank() : "desc should not be blank";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MortException {
        Task task = createTask();
        assert task != null : "Task should not be null";
        tasks.addTask(task);
        storage.save(tasks);
        return ui.getAddMessage(task, tasks.getSize());
    }

    private Task createTask() throws MortException {
        Task task = null;
        switch (this.taskType) {
        case TODO:
            task = new ToDo(description);
            break;
        case DEADLINE:
            task = createDeadline();
            break;
        case EVENT:
            task = createEvent();
            break;
        default:
            break;
        }

        return task;
    }

    private Deadline createDeadline() throws MortException {
        boolean hasTime = dateString.contains(" ");
        if (hasTime) {
            return new Deadline(this.description, Parser.convertStringToDateTime(this.dateString));
        } else {
            return new Deadline(this.description, Parser.convertStringToDate(this.dateString));
        }
    }

    private Event createEvent() throws MortException {
        boolean hasTime = dateString.contains(" ");
        if (hasTime) {
            return new Event(this.description, Parser.convertStringToDateTime(this.dateString));
        } else {
            return new Event(this.description, Parser.convertStringToDate(this.dateString));
        }
    }
}
