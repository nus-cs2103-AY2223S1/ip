package duke.commands;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>Deadline</code>.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Constructs a <code>AddDeadlineCommand</code> command.
     *
     * @param description Input from the user.
     */
    public AddDeadlineCommand(String description) {
        super(description);
    }

    /**
     * Adds a new <code>Deadline</code> into the <code>TaskList</code>
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeMissingArgumentException If the input is missing a description or time.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeMissingArgumentException {
        try {
            String[] str = description.substring(9).split(" /by ");
            Deadline deadline = new Deadline(str[0], str[1], false);
            tasks.add(deadline);
            int numberOfTasks = tasks.size();
            String response;
            if (numberOfTasks < 2) {
                response = "Got it. I've added this task:\n " + deadline
                        + "\nNow you have " + numberOfTasks + " task in the list.";
            } else {
                response = "Got it. I've added this task:\n " + deadline
                        + "\nNow you have " + numberOfTasks + " tasks in the list.";
            }
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description and/or the time of a deadline "
                    + "cannot be empty.");
        }
    }
}
