package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * Access all the available tasks
 */
public class AccessTasksCommand extends Command {
    /**
     * Creates an AccessTasksCommand
     * @param duke the instance of duke that spawns this command
     */
    public AccessTasksCommand(Duke duke) {
        super(duke);
    }


    @Override
    public void parse(Iterator<String> elements)
            throws IncompleteCommandException, InvalidCommandException {
        if (!elements.hasNext()) {
            throw new IncompleteCommandException("You need to specify what to do in tasks");
        }

        String thisElement = elements.next();

        switch (thisElement) {
        case "list":
            new ListTasksCommand(duke).printToGui();
            break;

        case "find":
            new FindTaskCommand(duke).parse(elements);
            break;

        case "add":
            new AddTaskCommand(duke).parse(elements);
            break;

        case "mark":
            new MarkTaskCommand(duke).parse(elements);
            break;

        case "unmark":
            new UnmarkTaskCommand(duke).parse(elements);
            break;

        case "delete":
            new DeleteTaskCommand(duke).parse(elements);
            break;

        case "help":
            printHelp();
            break;

        default:
            throw new InvalidCommandException(String.format("I'm cannot perform %s", thisElement));
        }
    }

    private void printHelp() {
        duke.sendMessage("You can specify "
                + "list, find, add, mark, unmark or delete "
                + "after the tasks arguments to use a procedure in tasks.");
        duke.sendMessage("For example, you can do \"tasks list\" to list all current tasks.");
        duke.sendMessage("Or, you could also do \"tasks add <args>\" to add a new task.");
        duke.sendMessage("Don't forget you can also specify the help command after any argument"
                + "to learn what you can do!");
    }
}
