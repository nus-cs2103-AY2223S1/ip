package duke.tools;

import duke.exception.TaskNotFoundException;
import java.util.HashMap;

/**
 * User input parser to make sense of user command.
 */
public class CommandParser extends Parser {
    private String[] keywords;
    private String input;

    private final HashMap<String, COMMANDS> commandMap = new HashMap<>();

    /**
     * Collection of commands recognised by Duke.
     */
    public enum COMMANDS {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a command parser to make sense of user commands.
     * @param input user input String.
     */
    public CommandParser(String input) {
        commandMap.put("bye", COMMANDS.BYE);
        commandMap.put("list", COMMANDS.LIST);
        commandMap.put("mark", COMMANDS.MARK);
        commandMap.put("unmark", COMMANDS.UNMARK);
        commandMap.put("delete", COMMANDS.DELETE);
        commandMap.put("todo", COMMANDS.TODO);
        commandMap.put("deadline", COMMANDS.DEADLINE);
        commandMap.put("event", COMMANDS.EVENT);
        this.input = input;
        this.keywords = input.split(" ", 2);
    }

    /**
     * Returns a constant COMMAND.
     * @return A command constant.
     * @throws TaskNotFoundException When user input command does not correspond to recognised command.
     */
    public COMMANDS getCommand() throws TaskNotFoundException {
        String command = this.keywords[0];
        if (!commandMap.containsKey(command)) {
            throw new TaskNotFoundException(input);
        }
        return commandMap.get(command);
    }

    /**
     * Returns task number according to order of tasks added.
     * @return Integer order of the task.
     */
    public int getTaskNo() {
        if (keywords.length < 2) {
            return 0;
        }
        return Integer.parseInt(this.keywords[1].substring(0,1));
    }
}
