package duke.tools;

import java.util.HashMap;

import duke.exception.TaskNotFoundException;

public class CommandParser extends Parser {
    private String[] keywords;
    private String input;

    private final HashMap<String, COMMANDS> commandMap = new HashMap<>();

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

    public COMMANDS getCommand() throws TaskNotFoundException {
        String command = this.keywords[0];
        if (!commandMap.containsKey(command)) {
            throw new TaskNotFoundException(input);
        }
        return commandMap.get(command);
    }

    public int getTaskNo() {
        if (keywords.length < 2) {
            return 0;
        }
        return Integer.parseInt(this.keywords[1].substring(0, 1));
    }
}
