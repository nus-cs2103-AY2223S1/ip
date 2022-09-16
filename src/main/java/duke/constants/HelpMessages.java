package duke.constants;

/**
 * List of help messages to be used by the 'help' command
 */
public final class HelpMessages {
    public static final String HELP_LIST = "View all your tasks";
    public static final String NEW_LINE = "\n";
    public static final String HELP_FIND = "Find a particular task based on a query";
    public static final String HELP_TODO = "Adds a new TODO task";
    public static final String HELP_DEADLINE = "Adds a new DEADLINE task";
    public static final String HELP_NOTE = "Adds a new NOTE";
    public static final String HELP_QUACK = "Returns a quack";
    public static final String HELP_EVENT = "Adds a new EVENT task";
    public static final String HELP_MARK = "Marks a task as done";
    public static final String HELP_UNMARK = "Marks a task as not done";
    public static final String HELP_HELP = "Displays help message";
    public static final String HELP_POSTPONE = "Postpones a task (Only applicable to DEADLINE and EVENT tasks)";
    public static final String HELP_DELETE = "Deletes a task";
    public static final String HELP_BYE = "Exits the program";
    public static final String HELP_MESSAGE = "QUACK! Deadline Duck is here to give u the list of supported commands!"
            + NEW_LINE + "'list' - " + HELP_LIST
            + NEW_LINE + "'find <QUERY>' - " + HELP_FIND
            + NEW_LINE + "'todo <TASK_NAME>' - " + HELP_TODO
            + NEW_LINE + "'deadline <TASK_NAME> /by <YYYY-MM-DD>' - " + HELP_DEADLINE
            + NEW_LINE + "'event <TASK_NAME> /at (date) [time]' - " + HELP_EVENT
            + NEW_LINE + "'note <NOTE_DESCRIPTION>' - " + HELP_NOTE
            + NEW_LINE + "'postpone <TASK_INDEX>' - " + HELP_POSTPONE
            + NEW_LINE + "'mark <TASK_INDEX>' - " + HELP_MARK
            + NEW_LINE + "'unmark <TASK_INDEX>' - " + HELP_UNMARK
            + NEW_LINE + "'help' - " + HELP_HELP
            + NEW_LINE + "'quack' - " + HELP_QUACK
            + NEW_LINE + "'delete <TASK_INDEX>' - " + HELP_DELETE
            + NEW_LINE + "'bye' - " + HELP_BYE;
}
