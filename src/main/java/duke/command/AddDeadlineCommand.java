package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * AddDeadlineCommand is a Command when the user wants to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {

    private String details;

    /**
     * Initializes an AddDeadlineCommand object.
     *
     * @param details The details of the task.
     */
    public AddDeadlineCommand(String details) {
        this.details = details;
    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String by;
        if (!details.contains("/by")) {
            throw new DukeException("OOPS!!! The deadline is required. (/by YYYY-MM-DD)");
        }
        String[] split = details.split(" /");
        String desc = split[0];
        if (desc.equals("") || desc.equals(" ")) {
            throw new DukeException("OOPS!!! Description of deadline is required.");
        }
        try {
            by = split[1].split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The deadline is required. (/by YYYY-MM-DD)");
        }
        Deadline deadline = new Deadline(desc, by);
        tasks.add(deadline);
        return deadline.toString();
    }
}
