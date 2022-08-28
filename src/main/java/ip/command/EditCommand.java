package ip.command;

import ip.TaskList;

import ip.exception.IndexNotSpecified;
import ip.exception.NoTaskFound;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Command to edit a task in the task list.
 */
public class EditCommand extends Command {
    /** Command given by the user */
    private final String commandGiven;
    /** Options following the command given */
    private final Scanner options;

    public EditCommand(String commandGiven, Scanner options) {
        this.commandGiven = commandGiven;
        this.options = options;
    }

    /**
     * Execute the given command to the specified task.
     *
     * @param taskList The task list to search the task for.
     * @throws IndexNotSpecified If no index number was specified.
     * @throws NoTaskFound If there is no task at the index number specified.
     */
    @Override
    public void execute(TaskList taskList) throws IndexNotSpecified, NoTaskFound {
        int index;
        try {
            index = options.nextInt();
        } catch (NoSuchElementException e) {
            throw new IndexNotSpecified();
        }
        switch (commandGiven) {
        case "mark":
            taskList.mark(index - 1);
            break;
        case "unmark":
            taskList.unmark(index - 1);
            break;
        case "delete":
            taskList.delete(index - 1);
            break;
        }
    }
}
