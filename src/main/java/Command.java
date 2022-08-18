import java.util.Map;
import java.util.HashMap;

public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, INVALID;

    private static final Map<String, Command> NAME_MAP = new HashMap<>();
    static {
        for (Command cmd : Command.values()) {
            NAME_MAP.put(cmd.name(), cmd);
        }
    }

    /**
     * Returns the Command corresponding to the input. This is case-insensitive.
     * If the input is unknown, an INVALID command will be returned.
     * 
     * @param cmd User command.
     * @return Command corresponding to the input.
     */
    public static Command getIfPresent(String cmd) {
        String formattedString = cmd.trim().toUpperCase();
        return NAME_MAP.getOrDefault(formattedString, Command.INVALID);
    }
}
