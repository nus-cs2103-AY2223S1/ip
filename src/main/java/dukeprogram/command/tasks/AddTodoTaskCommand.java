package dukeprogram.command.tasks;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.tasks.Task;
import dukeprogram.tasks.ToDo;
import dukeprogram.userinterface.Widget;
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
        duke.sendMessage("Okay, I've added the task ", new Widget(task.createLabelWidget()));
    }
}
