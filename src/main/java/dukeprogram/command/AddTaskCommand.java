package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.storage.SaveManager;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;


/**
 * Adds a task to the task list
 */
public class AddTaskCommand extends Command {

    /**
     * Creates an AddTaskCommand
     * @param duke the instance of duke that spawned this command
     */
    public AddTaskCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws IncompleteCommandException {
        if (!elements.hasNext()) {
            duke.sendMessage("Hmm, you need to tell me what you want to add...");
            throw new IncompleteCommandException("Usage: add <task_type> <task_name>");
        }

        String thisElement = elements.next();

        try {
            switch (thisElement) {
            case "todo":
                new AddTodoTaskCommand(duke).parse(elements);
                break;

            case "event":
                new AddEventTaskCommand(duke).parse(elements);
                break;

            case "deadline":
                new AddDeadlineTaskCommand(duke).parse(elements);
                break;

            default:
                duke.sendMessage(String.format("Sorry, %s is not a valid task type", thisElement));
            }
        } catch (InvalidCommandException e) {
            duke.sendMessage(e.getMessage());
        }
    }
}
