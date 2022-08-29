package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * UnmarkCommand removes mark on task based on index
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constuctor for UnmarkCommand
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public UnmarkCommand(String description) throws DukeException {
        try {
            description = description.split(" ")[1];
            this.index = Integer.parseInt(description);
        } catch (Exception e) {
            throw new DukeException("Invalid tasks");
        }
    }

    /**
     * Unmarks command
     *
     * @throws DukeException if task chosen is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("No such tasks found");
        } else {
            Task task = tasks.get(index - 1);
            task.setUndone();
            storage.save(tasks);
            System.out.println("Aiyah! I've marked this task as not done yet: ");
            System.out.println(task);
        }
    }
}
