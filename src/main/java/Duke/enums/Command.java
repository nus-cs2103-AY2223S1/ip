package Duke.enums;

/**
 * Command enum represents the possible commands that a user can provide to duke.
 * The value of these commands are the string the user requires inputting.
 */
public enum Command {

    HELP("help"),
    LIST("list"),
    FIND("find"),
    CHECK("check"),
    UNCHECK("uncheck"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private final String value; // Value provided by user.

    /**
     * The constructor for the Command enum.
     *
     * @param commandString the value representing the string value of the enum.
     */
    Command(String commandString) {
        value = commandString;
    }

    /**
     * Returns the Command object from the string value of the enum.
     *
     * @param value a string value of the enum represented by the value provided.
     * @return a command object of the command represented by the string.
     */
    public static Command getCommandFromValue(String value) {
        for (Command e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the value of the command enum.
     *
     * @return the string representation of a command enum.
     */
    public String getValue() {
        return value;
    }
}
