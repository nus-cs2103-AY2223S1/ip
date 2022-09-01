package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A AddCommand object can be used to handle the creation of Task objects.
 */
public class AddCommand extends Command {
    /*  The type of the task, whether it is a deadline, event or a todo. */
    protected String typeOfTask;
    /* The description of the task or what the task is. */
    protected String description;
    /* When the task is supposed to be due. */
    protected String deadline;

    /**
     * Creates a AddCommand object.
     *
     * @param typeOfTask The type of task, whether it is a deadline, event or a todo.
     * @param description The description of the task or what the task is.
     */
    public AddCommand(String typeOfTask, String description) {
        super(false);
        this.typeOfTask = typeOfTask;
        this.description = description;
    }

    /**
     * Creates a AddCommand object.
     *
     * @param typeOfTask The type of task, whether it is a deadline, event or a deadline.
     * @param description The description of the task or what the task is.
     * @param deadline The deadline or due date of the task.
     */
    public AddCommand(String typeOfTask, String description, String deadline) {
        super(false);
        this.typeOfTask = typeOfTask;
        this.description = description;
        this.deadline = deadline;
    }


    /**
     * Creates a deadline, event or a todo object based on the typeOfTask.
     * This object is then added to the TaskList and the relevant messages are printed out.
     *
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Task("null");
        if (typeOfTask.equals("deadline")) {
            task = new Deadline(description, deadline);
        } else if (typeOfTask.equals("event")) {
            task = new Event(description, deadline);
        } else if (typeOfTask.equals("todo")) {
            task = new ToDo(description);
        }

        tasks.addTask(task);
        storage.writeToFile(tasks);
        return ui.addTask(task) + ui.displayNumberOfTasks(tasks.getNumberOfTasks());
    }

}
