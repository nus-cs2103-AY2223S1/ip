package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The class that encapsulates the add command.
 */
public class AddCommand extends Command {

    /** The type of task to be added */
    private final String taskType;
    /** The description of the task to be added */
    private final String description;
    /** The date of the task to be added */
    private final LocalDate date;

    /**
     * The class constructor.
     *
     * @param taskType The type of task to be added.
     * @param description The description of the task
     *                    to be added.
     * @param date The date of the task to be added.
     */
    public AddCommand(String taskType, String description, LocalDate date) {
        this.taskType = taskType;
        this.description = description;
        this.date = date;
    }

    /**
     * Handles the execution behaviour of the adding of tasks.
     *
     * @param tasks   The list of tasks to add to.
     * @param storage The storage of data.
     * @return The reply of the Duke bot.
     * @throws DukeException If there is an error saving the
     *                       new data to the text file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = null;
        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "E":
            task = new Event(description, date);
            break;
        case "D":
            task = new Deadline(description, date);
            break;
        default:
            break;
        }
        tasks.add(task);
        storage.saveData(tasks);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks.";
    }

    /**
     * Returns the command type.
     *
     * @return "add".
     */
    @Override
    public String getCommand() {
        return "add";
    }

    /**
     * Returns the string representation of the add command.
     *
     * @return The string representation of the add command.
     */
    @Override
    public String toString() {
        return "add " + taskType + " " + description + " " + date;
    }
}
