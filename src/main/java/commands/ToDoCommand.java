package commands;

import data.Task;
import data.TaskList;
import data.ToDo;
import exceptions.DukeException;
import storage.Storage;

/**
 * Command to create a todo.
 */
public class ToDoCommand extends Command {
    private final String title;

    /**
     * Command to create a todo with title.
     * @param title Title of task
     */
    public ToDoCommand(String title) throws DukeException {
        if (title.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.title = title;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.add(new ToDo(title, false));
        storage.save(tasks);
        return "Got it. I've added this task: \n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list.";
    }
}
