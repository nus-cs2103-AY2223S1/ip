package command;

import java.time.LocalDate;

import exception.LunaException;
import storage.Storage;
// Import Tasks
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Encapsulates a user instruction to create a new task.
 *
 * @author fannyjian
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Initialises a command to create a new Todo.
     *
     * @param des Description of task.
     */
    public AddCommand(String des) {
        this.task = new Todo(des);
    }

    /**
     * Initialises a command to create a new Deadline or Event.
     *
     * @param type Type of Task.
     * @param des Description of Task.
     * @param date Date to be completed by.
     */
    public AddCommand(String type, String des, LocalDate date) {
        switch (type) {
        case "deadline":
            this.task = new Deadline(des, date);
            break;
        case "event":
            this.task = new Event(des, date);
            break;
        default:
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(this.task);
            ui.showAdded(tasks, this.task);
            storage.updateStorage(tasks);
        } catch (LunaException e) {
            ui.showError(e);
        }
    }
}
