package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;
import duke.exceptions.DukeEmptyCommandException;

/**
 * Represents a <code>Command</code> to add <code>ToDo</code>.
 */
public class AddToDoCommand extends Command {

    /**
     * Constructs a <code>AddToDoCommand</code> command.
     *
     * @param description description of command.
     */
    public AddToDoCommand(String description) {
        super(description);
    }

    /**
     * Add new ToDo based on input to TaskList.
     *
     * @param storage Storage object that communicate with local storage.
     */
    public void execute(Storage storage) {

        try {
            if (description.length() <= 5) {
                throw new DukeEmptyCommandException();
            }
            String information = description.substring(5);

            Task newTask = new ToDo(information);
            TaskList.add(newTask);
            Ui.printAddTask(newTask);
        } catch (DukeEmptyCommandException e) {
            Ui.printError(e.getMessage());
        }

    }
}
