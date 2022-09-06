package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * MarkCommand used to mark tasks as done
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constuctor for MarkCommand
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public MarkCommand(String description) throws DukeException {
        try {
            description = description.split(" ")[1];
            this.index = Integer.parseInt(description);
        } catch (Exception e) {
            throw new DukeException("Invalid tasks");
        }
    }

    /**
     * Marks command and prints out message to users depending on whether the
     * command was successful
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("No such tasks found");
        } else {
            Task task = tasks.get(index - 1);
            task.setDone();
            storage.save(tasks);
            return getMessage(task);
        }
    }

    public String getMessage(Task task) {
        String str = "Fuyoh! I've marked this task as done:";
        str += task;
        return str;
    }
}
