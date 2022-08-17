
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String commandType;

    Command(String commandType) {
        this.commandType = commandType;
    }

    public static Command parse(String commandType) throws DukeException {
        for (Command command : Command.values()) {
            if (command.commandType.equals(commandType)) {
                return command;
            }
        }
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}