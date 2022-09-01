package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>ToDo</code>.
 */
public class AddToDoCommand extends Command {

    /**
     * Constructs a <code>AddToDoCommand</code> command.
     *
     * @param description Input from the user.
     */
    public AddToDoCommand(String description) {
        super(description);
    }

    /**
     * Adds a new <code>ToDo</code> into the <code>TaskList</code>
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeMissingArgumentException If the input is missing a description.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeMissingArgumentException {
        try {
            ToDo todo = new ToDo(description.substring(5), false);
            tasks.add(todo);
            int numberOfTasks = tasks.getSize();
            String response;
            if (numberOfTasks < 2) {
                response = "Got it. I've added this task:\n " + todo
                        + "\nNow you have " + numberOfTasks + " task in the list.";
            } else {
                response = "Got it. I've added this task:\n " + todo
                        + "\nNow you have " + numberOfTasks + " tasks in the list.";
            }
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
