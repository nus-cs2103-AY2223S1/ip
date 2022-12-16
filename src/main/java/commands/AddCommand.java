package commands;

import java.time.LocalDate;

import exception.FredException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskType;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

/**
 * Add command for adding item to taskList.
 */
public class AddCommand extends Command {

    protected TaskType type;
    protected String description;
    protected LocalDate date;

    /**
     * Create AddCommand
     * @param type type of task
     * @param description description of task
     */
    public AddCommand(TaskType type, String description) {
        isExit = false;
        this.type = type;
        this.description = description;
    }

    /**
     * Create AddCommand
     * @param type type of task
     * @param description description of task
     * @param date date of task
     */
    public AddCommand(TaskType type, String description, LocalDate date) {
        isExit = false;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {

        if (type.equals(TaskType.TODO)) {
            tasks.add(new ToDo(description));
        } else if (type.equals(TaskType.EVENT)) {
            tasks.add(new Event(description, date));
        } else if (type.equals(TaskType.DEADLINE)) {
            tasks.add(new Deadline(description, date));
        }

        ui.storeMessage("Got it. I've added this task:\n"
                + tasks.getTask(tasks.size()).toString()
                + "\n"
                + "Now you have "
                + tasks.size()
                + " tasks in your list."
                + "\n");
    }
}
