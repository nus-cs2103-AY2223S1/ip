public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String representingString;

    private Command(String representingString) {
        this.representingString = representingString;
    }

    public static Command parseToEnum(String representingString) throws DukeException {
        for (Command command: Command.values()) {
            if (command.representingString.equals(representingString)) {
                return command;
            }
        }
        throw new DukeException("Please enter a valid request / command!");
    }
}
