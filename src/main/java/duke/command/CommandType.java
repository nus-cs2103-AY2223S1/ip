package duke.command;

import duke.exception.DukeException;

/**
 * CommandType represents the different types of commands.
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    private final String commandType;

    /**
     * Creates a CommandType with the corresponding command type.
     *
     * @param commandType Type of the Command.
     */
    CommandType(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns the corresponding CommandType based on the input String.
     *
     * @return The corresponding CommandType.
     * @throws DukeException If the command type String is invalid.
     */
    public static CommandType parse(String commandType) throws DukeException {
        for (CommandType command : CommandType.values()) {
            if (command.commandType.equals(commandType)) {
                return command;
            }
        }
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}
