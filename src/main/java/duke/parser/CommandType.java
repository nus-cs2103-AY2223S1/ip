
package duke.parser;

import java.util.HashMap;

/**
 * Enum class for command types
 */
public enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    LIST,
    UNDO,
    INVALID;

    /**
     * Helper function to incorporate enum so that the input could be case-insensitive.
     * @param str User command input.
     * @return the corresponding enum type.
     */
    public static CommandType map(String str) {
        HashMap<String, CommandType> mapping = new HashMap<>();
        int i;
        String[] inputList = {"bye", "mark", "unmark", "todo", "deadline", "event", "delete", "find", "list", "undo"};
        CommandType[] commandList = {BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, LIST, UNDO};
        for (i = 0; i < inputList.length; i++) {
            mapping.put(inputList[i], commandList[i]);
        }
        CommandType commandType = mapping.get(str.toLowerCase());
        if (commandType != null) {
            return commandType;
        } else {
            return INVALID;
        }
    }
}
