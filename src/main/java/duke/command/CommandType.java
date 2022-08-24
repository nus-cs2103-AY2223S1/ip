package duke.command;

import duke.exception.DukeException;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String representingString;

    private CommandType(String representingString) {
        this.representingString = representingString;
    }

    public static CommandType parseToCommand(String representingString) throws DukeException {
        for (CommandType commandType: CommandType.values()) {
            if (commandType.representingString.equals(representingString)) {
                return commandType;
            }
        }
        throw new DukeException("Please enter a valid request / command!");
    }
}
