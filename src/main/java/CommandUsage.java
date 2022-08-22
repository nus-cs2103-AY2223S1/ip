public enum CommandUsage {
    HELP("help"),
    BYE("bye"),
    LIST("list"),
    MARK("mark <task index>"),
    UNMARK("unmark <task index>"),
    TODO("todo <task description>"),
    EVENT("event <event description> /at <date>"),
    DEADLINE("deadline <task description> /by <date>"),
    DELETE("delete <task index>");

    private final String usageText;

    CommandUsage(final String usageText) {
        this.usageText = usageText;
    }

    @Override
    public String toString() {
        return usageText;
    }
}
