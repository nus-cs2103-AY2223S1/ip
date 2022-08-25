package scottie.instructions;

/**
 * An enum for the possible command names a user can enter.
 */
public enum Command {
    // The name strings are the commands that the user needs to type
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye"),
    FIND("find");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    /**
     * Returns the Command associated with the given name.
     * Returns null if there is no Command with the given name.
     *
     * @param name The name of the Command to return.
     * @return The Command associated with the given name.
     */
    public static Command fromName(String name) {
        for (Command c : Command.values()) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }
}
