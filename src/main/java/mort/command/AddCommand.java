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
    private String desc;
    /** Date of the task */
    private String dateStr;

    /**
     * Constructor to initialise the add command with the task type and description.
     * @param taskType The task type.
     * @param desc The task description.
     */
    public AddCommand(TaskType taskType, String desc) {
        this.taskType = taskType;
        this.desc = desc;
        assert !this.desc.isBlank() : "desc should not be blank";
    }

    /**
     * Constructor to initialise the add command with the task type, description and date.
     * @param taskType The task type.
     * @param desc The task description.
     * @param dateStr The task date.
     */
    public AddCommand(TaskType taskType, String desc, String dateStr) {
        this.taskType = taskType;
        this.desc = desc;
        this.dateStr = dateStr;
        assert !this.desc.isBlank() : "desc should not be blank";
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MortException {
        Task task = null;
        switch (this.taskType) {
        case TODO:
            task = new ToDo(desc);
            break;
        case DEADLINE:
            if (this.dateStr.contains(" ")) {
                task = new Deadline(this.desc, Parser.convertStringToDateTime(this.dateStr));
            } else {
                task = new Deadline(this.desc, Parser.convertStringToDate(this.dateStr));
            }
            break;
        case EVENT:
            if (this.dateStr.contains(" ")) {
                task = new Event(this.desc, Parser.convertStringToDateTime(this.dateStr));
            } else {
                task = new Event(this.desc, Parser.convertStringToDate(this.dateStr));
            }
            break;
        }
        assert task != null : "Task should not be null";
        tasks.addTask(task);
        storage.save(tasks);
        return ui.getAddMessage(task, tasks.getSize());
    }
}
