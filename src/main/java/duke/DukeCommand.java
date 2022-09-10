package duke;

/**
 * Valid Duke commands.
 */
public enum DukeCommand {
    EXIT("bye"),
    LIST("list"),
    FIND("find"),
    SORT("sortby"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    UNKNOWN("unknown");

    private final String command;

    DukeCommand(String command) {
        this.command = command;
    }

    /**
     * Reads an input String and converts to DukeCommand.
     *
     * @param inputCommand The input to read.
     * @return The appropriate DukeCommand, or UNKNOWN if invalid input.
     */
    public static DukeCommand read(String inputCommand) {
        for (DukeCommand dc : DukeCommand.values()) {
            if (dc.command.equals(inputCommand)) {
                return dc;
            }
        }

        return UNKNOWN;
    }
}
