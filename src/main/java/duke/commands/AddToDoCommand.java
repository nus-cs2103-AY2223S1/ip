package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;
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
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeMissingArgumentException If the input is missing a description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeMissingArgumentException {
        try {
            ToDo todo = new ToDo(description.substring(5), false);
            tasks.add(todo);
            ui.printAddTask(todo, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
