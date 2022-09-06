package duke.command;

/**
 * User commands and how to use them.
 */
public enum CommandUsage {
    HELP("h / help"),
    BYE("b / bye"),
    LIST("l / list"),
    MARK("m / mark <task index>"),
    UNMARK("u / unmark <task index>"),
    TODO("t / todo <task description>"),
    EVENT("e / event <event description> /at <date>"),
    DEADLINE("d / deadline <task description> /by <date>"),
    DELETE("del / delete <task index>"),
    FIND("f / find <keyword>");

    private final String usageText;

    CommandUsage(final String usageText) {
        this.usageText = usageText;
    }

    /**
     * Returns the correct usage of a command.
     *
     * @return String containing the correct command usage.
     */
    @Override
    public String toString() {
        return usageText;
    }
}
