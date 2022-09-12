package duke.command;

/**
 * An enum class that contains all the valid user commands.
 */
public enum CommandType {
    BYE("bye", 0, Operator.EQUAL),
    LIST("list", 0, Operator.EQUAL),
    MARK("mark", 1, Operator.EQUAL),
    UNMARK("unmark", 1, Operator.EQUAL),
    DELETE("delete", 1, Operator.EQUAL),
    TODO("todo", 0, Operator.GREATER_THAN),
    DEADLINE("deadline", 2, Operator.GREATER_THAN),
    EVENT("event", 2, Operator.GREATER_THAN),
    FIND("find", 1, Operator.EQUAL);

    private final String commandString;
    private final int numberOfArgs;
    private final Operator operator;

    CommandType(String commandString, int numberOfArgs, Operator operator) {
        this.commandString = commandString;
        this.numberOfArgs = numberOfArgs;
        this.operator = operator;
    }

    /**
     * Returns the String representation of the enum
     *
     * @return Returns the String representation of the enum
     */
    public String getString() {
        return this.commandString;
    }

    /**
     * Returns true if there are a valid number of arguments.
     * @param argsLength The length of the args
     * @return A boolean value
     */
    public boolean isNumberOfArgsCorrect(int argsLength) {
        switch (operator) {
        case GREATER_THAN:
            return argsLength > this.numberOfArgs;
        case EQUAL:
            return this.numberOfArgs == argsLength;
        default:
            return false;
        }
    }
}
