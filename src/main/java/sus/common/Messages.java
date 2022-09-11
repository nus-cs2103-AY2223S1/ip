package sus.common;

/**
 * Container for messages shown to user.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Hello! I'm Sus.\nWhat can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_TASK_UPDATE_STATUS = "I've marked this task as %s.";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:";
    public static final String MESSAGE_TASK_NUMBER = "Now you have %d tasks in the list.";
    public static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:";

    public static final String MESSAGE_OUTPUT_COLOUR_SET = "Set output colour to %s";
    public static final String MESSAGE_UPDATED_DESCRIPTION = "Task has been updated.\n%s";

    public static final String MESSAGE_INVALID_COMMAND = "Command not found. Please do help command.";
    public static final String MESSAGE_INVALID_ARGUMENTS = "Incorrect number of arguments.";
    public static final String MESSAGE_MISSING_SEPARATOR = "Missing %s. Check commands list.";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Description cannot be empty.";
    public static final String MESSAGE_EMPTY_DATE = "Date cannot be empty.";
    public static final String MESSAGE_WRONG_DATE_FORMAT = "Please input date as yyyy-mm-dd";
    public static final String MESSAGE_TASK_NOT_SPECIFIED = "Please specify the task number (check list).";
    public static final String MESSAGE_INVALID_NUMBER = "The task number you have specified is invalid.";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "The task is already marked as done.";
    public static final String MESSAGE_TASK_ALREADY_UNMARKED = "The task is already unmarked.";
}
