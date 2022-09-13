package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.Task;
import dukeprogram.ToDo;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

/**
 * AddTodoTaskCommand can add a task to the current task list of the Duke instance
 */
public class AddTodoTaskCommand extends Command {

    /**
     * Creates a AddTodoTaskCommand
     * @param duke the instance of duke that spawns the command
     */
    public AddTodoTaskCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        if (!elements.hasNext()) {
            throw new InvalidCommandException("A name must be provided for this Todo task");
        }

        Task task = new ToDo(StringUtilities.concatByDelimiter(elements, " "));
        duke.getTaskList().add(task);
        duke.sendMessage("Okay, I've added the task " + task);
    }
}
