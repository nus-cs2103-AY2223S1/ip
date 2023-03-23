package duke;

/**
 * This enum encapsulates a Command given by the user to Duke.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    LISTALLONDATE("listallondate"),
    FIND("find");

    private String name;

    /**
     * Constructor for the enumeration constants.
     *
     * @param name The name of the duke.Command
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Gets the command with the given name.
     *
     * @param name the name of the command (i.e. what the user will input in the CLI)
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
