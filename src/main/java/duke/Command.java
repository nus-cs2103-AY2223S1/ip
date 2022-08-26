package duke;

public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    BYE("bye");

    private final String inputCommand;

    /**
     * Constructor for the command enum.
     *
     * @param command input command
     */
    Command(String command) {
        this.inputCommand = command;
    }

    /**
     * Returns the command if given a correct command.
     *
     * @param command Input command by user.
     * @return Enum value corresponding to the input command.
     * @throws IllegalArgumentException if invalid command is given.
     */
    public static Command getCommand(String command) throws IllegalArgumentException {
        for (Command c : Command.values()) {
            if (command.equals(c.inputCommand)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No such available command, " + "\"" + command + "\" please try again.\n");
    }
}

