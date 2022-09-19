package duke.handlers;

import duke.exceptions.DukeException;
import java.util.HashMap;

/**
 * Represents a map containing all duke commands.
 */
public class DukeCommandMap {
    protected HashMap<String, DukeCommand> commandMap = new HashMap<>();

    /**
     * Constructs the command map with all the available commands.
     */
    public DukeCommandMap () {
        commandMap.put("list", new ListTasksCommand());
        commandMap.put("mark", new MarkAsDoneCommand());
        commandMap.put("unmark", new MarkAsUndoneCommand());
        commandMap.put("todo", new AddTodoCommand());
        commandMap.put("deadline", new AddDeadlineCommand());
        commandMap.put("event", new AddEventCommand());
        commandMap.put("delete", new DeleteTaskCommand());
        commandMap.put("find", new FindTaskCommand());
        commandMap.put("revent", new AddRecurringEventCommand());
        commandMap.put("save", new SaveCommand());
        commandMap.put("bye", new ExitCommand());
        commandMap.put("help", new HelpCommand());
    }

    /**
     * Find the command that matches with the user input.
     *
     * @param keyword The command keyword user entered.
     * @return The command matched.
     * @throws DukeException If the command is not found.
     */
    public DukeCommand getCommand(String keyword) throws DukeException {
        if (!commandMap.containsKey(keyword)) {
            throw new DukeException("Command not found! Type 'help' to see all the commands.");
        }
        return commandMap.get(keyword);
    }
}
