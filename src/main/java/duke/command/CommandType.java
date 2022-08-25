package duke.command;

import duke.exception.DukeException;

/**
 * Represents the type of user command.
 */
public enum CommandType {

    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String command;

    /**
     * Constructs a new CommandType.
     *
     * @param command Type of command.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Returns a CommandType based on the command given.
     *
     * @param command String representation of the command given.
     * @return CommandType based on the command.
     * @throws DukeException If the command given does not have a representing CommandType.
     */
    public static CommandType parse(String command) throws DukeException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.command.equals(command)) {
                return commandType;
            }
        }
        throw new DukeException("Sorry, there is no such command!");
    }

}
