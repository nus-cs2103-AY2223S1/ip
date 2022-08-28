package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.ui.Ui;

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
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddMessage(task, tasks.getSize());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
