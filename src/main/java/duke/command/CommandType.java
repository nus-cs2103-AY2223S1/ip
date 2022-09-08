package duke.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of command types the program recognises.
 */
public enum CommandType {
    TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, SAVE, FIND;

    /**
     * Mapping of string input to its command type.
     */
    public static final HashMap<String, CommandType> COMMAND_MAP = new HashMap<>(
            Map.of(
                    "todo", TODO,
                    "deadline", DEADLINE,
                    "event", EVENT,
                    "list", LIST,
                    "bye", BYE,
                    "mark", MARK,
                    "unmark", UNMARK,
                    "delete", DELETE,
                    "save", SAVE,
                    "find", FIND
    ));
}
