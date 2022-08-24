package commands;

import storage.Storage;

// Import Tasks
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import ui.Ui;


/**
 * Encapsulates a user instruction to create a new task.
 *
 * @author fannyjian
 */public class AddCommand extends Command {
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
    public AddCommand(String type, String des, String date) {
        switch (type) {
        case "deadline":
            this.task = new Deadline(des, date);
            break;
        case "event":
            this.task = new Event(des, date);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAdded(tasks, this.task);
        storage.updateStorage(tasks);
    }
}
