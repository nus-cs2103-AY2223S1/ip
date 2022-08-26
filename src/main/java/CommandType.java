import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    BYE(), LIST(), EMPTY(""), MARK(), UNMARK(), TODO(), DEADLINE(), EVENT(), DELETE();

    private static final Map<String, CommandType> BY_COMMAND_TYPE = new HashMap<>();

    static {
        for (CommandType c : values()) {
            BY_COMMAND_TYPE.put(c.commandType, c);
        }
    }

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    CommandType() {
        commandType = name();
    }

    public String getString() {
        return commandType.toLowerCase();
    }

    public static CommandType fromString(String commandType) {
        return BY_COMMAND_TYPE.get(commandType);
    }
}
