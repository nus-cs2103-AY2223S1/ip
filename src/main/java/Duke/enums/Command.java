package Duke.enums;

/**
 * Command enum represents the possible commands that a user can provide to duke.
 * The value of these commands are the string the user requires inputting.
 */
public enum Command {

    LIST("list"),
    CHECK("check"),
    UNCHECK("uncheck"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private final String value; // Value provided by user.

    /**
     * Constructor of a command enum.
     *
     * @param value The string representation of the command.
     */
    Command(String value) {
        this.value = value;
    }

    /**
     * Give the command enum base on the string provided.
     *
     * @param value String representing the command.
     * @return a Command enum representing the users string provided.
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
     * @return the value of the command
     */
    public String getValue() {
        return value;
    }

}
