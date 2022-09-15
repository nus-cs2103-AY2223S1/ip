package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>ToDo</code>.
 */
public class AddToDoCommand extends Command {

    private static final int DESCRIPTION_INDEX = 5;

    private static final String MESSAGE_ARGUMENT_MISSING = "OOPS!!! The description and/or the time of a todo "
            + "cannot be empty.";
    private static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\nNow you have %d tasks "
            + "in the list.";

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
            assert !description.substring(DESCRIPTION_INDEX).isBlank() : MESSAGE_ARGUMENT_MISSING;
            ToDo todo = new ToDo(description.substring(DESCRIPTION_INDEX), false);
            tasks.add(todo);
            int numberOfTasks = tasks.getSize();
            String response = String.format(MESSAGE_SUCCESS, todo, numberOfTasks);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException(MESSAGE_ARGUMENT_MISSING);
        }
    }
}
