package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * UnmarkCommand removes mark on task based on index
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Creates an UnmarkCommand to mark a task as undone
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public UnmarkCommand(String description) throws DukeException {
        try {
            assert description.split(" ")[0].equals("unmark") : "Keyword should be unmark for UnmarkCommand";
            description = description.split(" ")[1];
            this.index = Integer.parseInt(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Fill in index of task to delete");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid tasks");
        }
    }

    /**
     * Unmarks command
     *
     * @return @inheritDoc
     * @throws DukeException if task chosen is out of bounds
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("No such tasks found");
        } else {
            Task task = tasks.get(index - 1);
            task.setUndone();
            storage.save(tasks);
            return getMessage(task);
        }
    }

    public String getMessage(Task task) {
        String str = "Aiyah! I've marked this task as not done yet: \n";
        str += task;
        return str;
    }
}
