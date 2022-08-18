public enum DukeCommand {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("unknown");

    private final String command;

    DukeCommand(String command) {
        this.command = command;
    }

    public static DukeCommand read(String inputCommand) {
        for (DukeCommand dc : DukeCommand.values()) {
            if (dc.command.equals(inputCommand)) {
                return dc;
            }
        }

        return UNKNOWN;
    }
}
