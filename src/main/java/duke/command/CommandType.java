package duke.command;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE, SAVE, FIND;

    public static final HashMap<String, CommandType> commandMap = new HashMap<>(Map.of(
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
