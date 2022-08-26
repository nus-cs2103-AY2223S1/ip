package blob.commands;

/**
 * The enum contains all the valid command words that can be used to invoke a command
 */
public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    BYE("bye");

    private String trigger;

    /**
     * @param trigger the trigger that invokes the command type
     */
    CommandType(String trigger) {
        this.trigger = trigger;
    }

    /**
     * Returns the trigger of the command type
     *
     * @return the trigger of the command type
     */
    @Override
    public String toString() {
        return trigger;
    }
}
