
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public static CommandType parse(String commandType) throws DukeException {
        for (CommandType command : CommandType.values()) {
            if (command.commandType.equals(commandType)) {
                return command;
            }
        }
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}