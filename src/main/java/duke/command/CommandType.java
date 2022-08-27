package duke.command;

/**
 * An enum class that lists out all command types. This can be used to determine the runtime type of a command object.
 */
public enum CommandType {
    UNKNOWN(""),
    ERROR(null),
    EXIT("bye"),
    DISPLAY_LIST("list"),
    MARK_DONE("mark"),
    MARK_UNDONE("unmark"),
    DELETE("delete"),
    FIND("find"),
    ADD_EVENT("event"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline");

    private final String commandString;

    /**
     * Initialises an enum value.
     * The standard constructor.
     * @param commandString The string representation of a CommandType.
     */
    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the string representation, also the command instruction, of a command type
     * @return The string representation of a CommandType.
     */
    @Override
    public String toString() {
        return commandString;
    }
}
