package duke.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import duke.exception.InvalidActionException;

/**
 * Represents all Actions that Duke supports.
 */
public enum Action {
    DONOTHING,
    //    Level_1
    GREET, ECHO, EXIT,
    //    Level_2
    ADD, LIST,
    //    Level_3
    MARK, UNMARK,
    //    Level_4
    TODO, EVENT, DEADLINE,
    //    Level_5
    //    No new actions is added
    //    Level_6
    DELETE,
    //    Level_7
    SAVE, READ,
    //    Level_8
    //    No new actions is added
    //    Level-9
    FIND;

    /**
     * The HashMap stores the standardFormat of an Action.
     */
    private static HashMap<Action, String> actionFormatMap = new HashMap<>();

    /**
     * The HashMap stores the String representation of an Action.
     */
    private static HashMap<String, Action> stringActionMap = new HashMap<>();

    static {
        HashMap<Action, String> map = actionFormatMap;
        map.put(Action.DONOTHING, "");
        //        Level_1
        map.put(Action.GREET, "greet");
        map.put(Action.EXIT, "bye");
        map.put(Action.ECHO, "echo");
        //        Level_2
        map.put(Action.ADD, "add [Name]");
        map.put(Action.LIST, "list");
        //        Level_3
        map.put(Action.MARK, "mark [ID of task]");
        map.put(Action.UNMARK, "unmark [ID of task]");
        //        Level_4
        map.put(Action.TODO, "todo [Name]");
        map.put(Action.EVENT, "event [Event Name] /at [Event Time]");
        map.put(Action.DEADLINE, "deadline [Deadline Name] /by [Deadline Time]");
        //        Level_5
        //        No Action is added.
        //        Level_6
        map.put(Action.DELETE, "delete [ID of Task]");
        //        Level_7
        map.put(Action.SAVE, "save");
        map.put(Action.READ, "read");
        //        Level_8
        //        No action added.
        //        Level_9
        map.put(Action.FIND, "find [keyword]");
    }

    static {
        HashMap<String, Action> map = stringActionMap;
        map.put("", Action.DONOTHING);
        //        Level_1
        map.put("greet", Action.GREET);
        map.put("bye", Action.EXIT);
        //        map.put("echo", Action.ECHO);
        //        Level_2
        //        map.put("add", Action.ADD);
        map.put("list", Action.LIST);
        //        Level_3
        map.put("mark", Action.MARK);
        map.put("unmark", Action.UNMARK);
        //        Level_4
        map.put("todo", Action.TODO);
        map.put("event", Action.EVENT);
        map.put("deadline", Action.DEADLINE);
        //        Level_5
        //        No Action is added.
        //        Level_6
        map.put("delete", Action.DELETE);
        //        Level_7
        map.put("save", Action.SAVE);
        map.put("read", Action.READ);
        //        Level_9
        map.put("find", Action.FIND);
    }

    /**
     * Returns the String representation of an Action.
     * @param action The Action to convert.
     * @return The String representation of an Action.
     */
    public static String getString(Action action) {
        for (Map.Entry<String, Action> m : stringActionMap.entrySet()) {
            if (m.getValue().equals(action)) {
                return m.getKey();
            }
        }
        return "";
    }

    /**
     * Returns a mapped Action of the given String.
     * @param action The String to convert to Action
     * @return The mapped Action of the given String.
     * @throws InvalidActionException If the mapping of the given String is undefined.
     */
    public static Action getAction(String action) throws InvalidActionException {
        return Optional.ofNullable(action)
                .map(x -> Action.stringActionMap.get(x.trim()))
                .orElseThrow(() -> new InvalidActionException(action));
    }

    /**
     * Returns the standard format of the given Action.
     * @param action The action.
     * @return String of standard format.
     */
    public static String getFormat(Action action) {
        return Optional.ofNullable(action)
                .map(x -> Action.actionFormatMap.get(x))
                .orElse("No standard format is provided");
    }
}
