package duke;

public enum Command {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    UNKNOWN("unknown");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command read(String input) {
        for (Command c : Command.values()) {
            if (c.command.equals(input)) {
                return c;
            }
        }
        return UNKNOWN;
    }

}
