package sus.common;

/**
 * Container for messages shown to user.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Hello! I'm Sus.\nWhat can I do for you?";
    public static final String MESSAGE_EXIT = "BYE.";

    public static final String MESSAGE_TASK_UPDATE_STATUS = "MARKED task as %s.";
    public static final String MESSAGE_TASK_ADDED = "ADDED task:";
    public static final String MESSAGE_TASK_DELETED = "REMOVED task:";
    public static final String MESSAGE_TASK_NUMBER = "You have %d tasks in the list.";
    public static final String MESSAGE_TASK_LIST = "LIST of tasks:";

    public static final String MESSAGE_OUTPUT_COLOUR_SET = "CHANGED output colour to %s.";
    public static final String MESSAGE_UPDATED_DESCRIPTION = "UPDATED task description:\n%s";

    public static final String MESSAGE_INVALID_COMMAND = "Command not found. Please do help command.";
    public static final String MESSAGE_INVALID_ARGUMENTS = "Incorrect number of arguments.";
    public static final String MESSAGE_MISSING_SEPARATOR = "Missing %s. Check commands list.";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Please enter a description.";
    public static final String MESSAGE_EMPTY_DATE = "Please enter a date.";
    public static final String MESSAGE_WRONG_DATE_FORMAT = "Please input date as yyyy-mm-dd";
    public static final String MESSAGE_TASK_NOT_SPECIFIED = "Please specify the task number (check list).";
    public static final String MESSAGE_INVALID_NUMBER = "Please specify a valid task number.";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "The task is already MARKED.";
    public static final String MESSAGE_TASK_ALREADY_UNMARKED = "The task is already UNMARKED.";
    public static final String MESSAGE_COLOUR_NOT_SUPPPORTED = "The colour is UNSUPPORTED. Please check help.";
}
