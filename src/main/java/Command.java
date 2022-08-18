public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private String name;

    /**
     * Constructor for the enumeration constants.
     *
     * @param name The name of the Command
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Gets the command with the given name.
     *
     * @param name the name of the command (i.e. what the user will enter in the CLI)
     * @return corresponding command if the name is valid
     *         null otherwise
     */
    public static Command get(String name) {
        for (Command c : values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
