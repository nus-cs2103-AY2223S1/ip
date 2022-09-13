package duke;

/**
 * The enum duke.Duke commands.
 */
public enum DukeCommands {
    /**
     * Exit duke commands.
     */
    EXIT("bye"),
    /**
     * List duke commands.
     */
    LIST("list"),
    /**
     * Mark duke commands.
     */
    MARK("mark"),
    /**
     * Unmark duke commands.
     */
    UNMARK("unmark"),
    /**
     * Todo duke commands.
     */
    TODO("todo"),
    /**
     * Deadline duke commands.
     */
    DEADLINE("deadline"),
    /**
     * Event duke commands.
     */
    EVENT("event"),
    /**
     * Delete duke commands.
     */
    DELETE("delete");

    private final String text;

    DukeCommands(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
