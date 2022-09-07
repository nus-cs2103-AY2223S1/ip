package duke.command;

import duke.exception.DukeException;

/**
 * Represents the different types of Command.
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
    FIND("find"),
    UPDATE("update");

    private final String representingString;

    /**
     * Creates a CommandType with it's representing string.
     * @param representingString The string that the CommandType represent.
     */
    CommandType(String representingString) {
        this.representingString = representingString;
    }

    /**
     * Returns the corresponding CommandType to the representingString.
     *
     * @param representingString The string to be parsed.
     * @return The CommandType corresponding to the representingString.
     * @throws DukeException If there is no CommandType that the representingString can parse to.
     */
    public static CommandType parseToCommand(String representingString) throws DukeException {
        for (CommandType commandType: CommandType.values()) {
            if (commandType.representingString.equals(representingString)) {
                return commandType;
            }
        }
        throw new DukeException("Please enter a valid request / command!");
    }
}
