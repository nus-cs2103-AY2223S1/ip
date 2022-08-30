package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * DeleteCommand used to delete tasks
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constuctor for DeleteCommand
     *
     * @param description String representation of task number to be marked
     * @throws DukeException if user did not type in a correct task number
     */
    public DeleteCommand(String description) throws DukeException {
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
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("No such task found");
        } else {
            Task task = tasks.remove(index - 1);
            storage.save(tasks);
            String str = "Noted. I've removed this task:\n";
            str += task.toString() + '\n';
            str += "Now you have " + tasks.size() + " task(s) in the list";
            return str;
        }

    }
}
