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

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType parse(String command) throws DukeException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.command.equals(command)) {
                return commandType;
            }
        }
        throw new DukeException("Sorry, there is no such command!");
    }

}
