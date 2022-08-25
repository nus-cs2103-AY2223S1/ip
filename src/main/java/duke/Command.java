package duke;

/**
 * An enum class that contains all the valid user commands.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Method to get the String representation of the enum
     * @return Returns the String representation of the enum
     */
    public String getString() {
        return this.commandString;
    }
}