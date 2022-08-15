public enum Command {
    TODO("todo <String>"),
    DEADLINE("deadline <String> /by <String>"),
    EVENT("event <String> /at <String>"),
    LIST("list"),
    DELETE("delete <Integer>"),
    BYE("bye"),
    MARK("mark <Integer>"),
    UNMARK("unmark <Integer>");

    private final String format;
    Command(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }

    @Override
    public String toString() {
        return this.name();
    }


    public static boolean isValidCommand(String commandString) {
        for (Command cmd : Command.values()) {
            if (cmd.name().toLowerCase().equals(commandString)) {
                return true;
            }
        }
        return false;
    }
}