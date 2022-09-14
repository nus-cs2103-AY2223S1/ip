package duke;

/**
 * Enum class that contains all the possible type of user input.
 */
public enum Command {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    UNKNOWN("unknown");

    private final String command;

    Command(String command) {
        this.command = command;
    }


    /**
     * Reads the user input and matches with the command.
     * @param input The User input.
     * @return The Command that matches the user input.
     */
    public static Command read(String input) {
        for (Command c : Command.values()) {
            if (c.command.equals(input)) {
                return c;
            }
        }
        return UNKNOWN;
    }

}
