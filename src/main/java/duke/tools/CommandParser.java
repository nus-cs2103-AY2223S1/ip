package duke.tools;

import java.util.HashMap;
import java.util.Locale;

import duke.exception.TaskNotFoundException;

/**
 * User input parser to make sense of user command.
 */
public class CommandParser extends Parser {
    private String[] keywords;
    private String input;

    private final HashMap<String, Commands> commandMap = new HashMap<>();

    /**
     * Collection of commands recognised by Duke.
     */
    public enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        SORT
    }

    /**
     * Collection of sorting criteria recognised by Duke.
     */
    public enum Sorting {
        DATES,
        NAME,
        TASK,
        DONE
    }

    /**
     * Creates a command parser to make sense of user commands.
     * @param input user input String.
     */
    public CommandParser(String input) {
        commandMap.put("bye", Commands.BYE);
        commandMap.put("list", Commands.LIST);
        commandMap.put("mark", Commands.MARK);
        commandMap.put("unmark", Commands.UNMARK);
        commandMap.put("delete", Commands.DELETE);
        commandMap.put("todo", Commands.TODO);
        commandMap.put("deadline", Commands.DEADLINE);
        commandMap.put("event", Commands.EVENT);
        commandMap.put("find", Commands.FIND);
        commandMap.put("sort", Commands.SORT);

        this.input = input;
        this.keywords = input.split(" ", 2);
    }

    /**
     * Returns a Command.
     * @return A Command constant.
     * @throws TaskNotFoundException When user input command does not correspond to recognised command.
     */
    public Commands getCommand() throws TaskNotFoundException {
        String command = this.keywords[0].toLowerCase(Locale.ROOT);
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
        return Integer.parseInt(this.keywords[1].substring(0, 1));
    }

    /**
     * Returns target of search.
     * @return Word that is searched for.
     */
    public String getWord() {

        return this.keywords[1];
    }

    /**
     * Returns the sorting criteria for the list.
     * @return Criteria for sorting
     */
    public Sorting getSortCriteria() {

        return Sorting.valueOf(this.keywords[1].toUpperCase(Locale.ROOT));
    }
}
