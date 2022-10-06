package util;

/**
 * Utility class containing messages used in responses.
 * They are compiled here to provide ease of access to would-be
 * translators for the application.
 */
public class TextUtils {

    // ERROR MESSAGES
    public static final String UNKNOWN_COMMAND_ERROR = "UNKNOWN COMMAND! USE \"HELP\" TO SEE A LIST OF COMMANDS!";
    public static final String FIND_COMMAND_ERROR = "PLEASE PREFIX YOUR SEARCH TERMS WITH \"--\"!";
    public static final String TENTATIVE_COMMAND_ERROR = "INVALID TENTATIVE COMMAND!";
    public static final String DATE_IN_PAST_ERROR = "DATE IS IN THE PAST!";
    public static final String DATE_FORMAT_ERROR = "DATE AND TIME NUMBERS ARE OUT OF RANGE!";
    public static final String NON_NUMBER_ERROR = "ARGUMENT IS NOT A NUMBER!";
    public static final String MUST_BE_POSITIVE_ERROR = "ARGUMENT MUST BE A POSITIVE INTEGER!";
    public static final String MALFORMED_COMMAND_ERROR = "MALFORMED COMMAND!";
    public static final String ARGUMENT_SYNTAX_ERROR = "ARGUMENT HAS THE WRONG FORMAT!";
    public static final String EMPTY_INPUT_ERROR = "INPUT CANNOT BE EMPTY!";
    public static final String DUPLICATE_TASK_ERROR = "TASK ALREADY EXISTS!";
    public static final String INDEX_OUT_OF_RANGE_ERROR = "INDEX IS OUT OF RANGE! YOUR TASK LIST HAS %1$d ITEMS.";

    // HELP MESSAGES
    public static final String TODO_COMMAND_HELP = "Add a \"Todo\" task to the Task List.\n"
                                                   + "FORMAT: todo <task name>\n"
                                                   + "EXAMPLE: todo buy milk";
    public static final String DEADLINE_COMMAND_HELP = "Add a \"Deadline\" task to the Task List.\n"
                                                       + "FORMAT: deadline <task name> /by <date> <time>\n"
                                                       + "EXAMPLE: deadline submit assignment /by 2020-10-10 2359";
    public static final String EVENT_COMMAND_HELP = "Add an \"Event\" task to the Task List.\n"
                                                    + "FORMAT: event <task name> /at <date> <time>\n"
                                                    + "EXAMPLE: event attend meeting /at 2020-10-10 2359";
    public static final String LIST_COMMAND_HELP = "List all tasks in the Task List.\n"
                                                   + "FORMAT: list";
    public static final String MARK_COMMAND_HELP = "Mark a task as done.\n"
                                                   + "FORMAT: mark <task number>\n"
                                                   + "EXAMPLE: mark 1";
    public static final String UNMARK_COMMAND_HELP = "Mark a task as not done.\n"
                                                     + "FORMAT: unmark <task number>\n"
                                                     + "EXAMPLE: unmark 1";
    public static final String DELETE_COMMAND_HELP = "Delete a task from the Task List.\n"
                                                     + "FORMAT: delete <task number>\n"
                                                     + "EXAMPLE: delete 1";
    public static final String FIND_COMMAND_HELP = "Find tasks with the keyword.\n"
                                                   + "FORMAT: find --<keyword>\n"
                                                   + "EXAMPLE: find --assignment";
    public static final String TEACH_COMMAND_HELP = "Teach Henry a new word.\n"
                                                    + "FORMAT: teach <keyword> is a <meaning>\n"
                                                    + "EXAMPLE: teach potato is a plant";
    public static final String TENTATIVE_COMMAND_HELP = "Add a tentative task to the Task List.\n"
                                                        + "FORMAT: tentative <task index> "
                                                        + "<date> <time>>\n"
                                                        + "EXAMPLE: tentative 1 2020-10-10 2359\n"
                                                        + "SECOND FORMAT: tentative <task index>"
                                                        + " --confirm <date index>\n"
                                                        + "EXAMPLE: tentative 1 --confirm 1";
    public static final String HELP_COMMAND_HELP = "Show this help message.\n"
                                                   + "FORMAT: help";
    public static final String BYE_COMMAND_HELP = "Exit Henry.\n"
                                                  + "FORMAT: bye";

    // OTHER MESSAGES
    public static final String TASKS_SAVED_MESSAGE = "Goodbye! Your task list has been saved!";

}
