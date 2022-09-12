package ava.command;

import java.time.LocalDateTime;

import ava.Ui;
import ava.exception.AvaException;
import ava.exception.NoDescriptionException;
import ava.exception.UnknownCommandException;
import ava.exception.WrongTimeFormatException;
import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.Todo;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Add" command.
 */
public class Add extends Command {
    private final Task task;

    /**
     * The constructor for Add task
     *
     * @param task The Task object.
     */
    private Add(Task task) {
        this.task = task;
    }

    /**
     * AddCommand factory method.
     *
     * @param type The type of task.
     * @param chat The user's input content.
     * @param time Time description of the specific task.
     * @return An AddCommand.
     * @throws UnknownCommandException If the task type is unknown.
     * @throws NoDescriptionException If the description is empty.
     * @throws WrongTimeFormatException If the date is not in yyyy-MM-dd HH:mm format.
     */
    public static Add of(String type, String chat, LocalDateTime time) throws AvaException {
        Add command;
        switch(type) {
        case "todo":
            command = new Add(new Todo(chat, false));
            break;
        case "deadline":
            command = new Add(new Deadline(chat, false, time));
            break;
        case "event":
            command = new Add(new Event(chat, false, time));
            break;
        default:
            throw new UnknownCommandException(type);
        }
        return command;
    }

    /**
     * Executes process to delete a specific task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to read/write commands to file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.write(tasks.getTasks());
        return ui.showAddOnTask(tasks, (tasks.size() - 1));
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
