public enum Commands {
    TODO("todo"), EVENT("event"), DEADLINE("deadline"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), LIST("list"), BYE("bye");
    public final String command;

    Commands(String command) {
        this.command = command;
    }

    public static Commands getCommand(String command) throws IllegalArgumentException {
        for (Commands c : Commands.values()) {
            if (c.command.equals(command)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid command");
    }
}
