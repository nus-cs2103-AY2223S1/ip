public enum Command {
    // The name strings are the commands that the user needs to type
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command fromName(String name) {
        for (Command c : Command.values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
