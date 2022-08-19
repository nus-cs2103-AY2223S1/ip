import java.util.HashMap;

public enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    LIST;

    /**
     * Helper function to incorporate enum so that the input could be case-insensitive.
     * @param str User command input.
     * @return the corresponding enum type.
     */
    public static CommandType map(String str) {
        HashMap<String, CommandType> mapping = new HashMap<>();
        int i;
        String[] inputList = {"bye", "mark", "unmark", "todo", "deadline", "event", "delete", "find", "list"};
        CommandType[] commandList = {BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, LIST};
        for (i = 0; i < inputList.length; i++) {
            mapping.put(inputList[i], commandList[i]);
        }
        System.out.println(str.toLowerCase());
        return mapping.get(str.toLowerCase());
    }
}