package duke.common;

/**
 * Container for messages to show the user.
 */
public class Messages {
    public static final String MESSAGE_GREETING = "Hello! I'm Pip :)\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Goodbye and see you again soon!";

    public static final String MESSAGE_INVALID_COMMAND = "Sorry! I don't know what that means :(";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Please specify a date in this format: %s!";

    public static final String MESSAGE_CREATE_FILE_ERROR = "There has been an error creating your data file.";
    public static final String MESSAGE_SAVE_FILE_ERROR = "There has been an error saving your data file.";
    public static final String MESSAGE_LOAD_FILE_ERROR = "There has been an error loading your data file.";
    public static final String MESSAGE_RETRIEVE_FILE_ERROR = "There has been an error retrieving your previous data file.";
    public static final String MESSAGE_EMPTY_FILE_ERROR = "You have not made changes to your task list yet!";

    public static final String MESSAGE_TASK_ADDED = "Task added:\n  ";
    public static final String MESSAGE_TASK_REMOVED = "Task removed:\n  ";
    public static final String MESSAGE_TASK_DONE = "Task marked as done:\n  ";
    public static final String MESSAGE_TASK_NOT_DONE = "Task marked as not done:\n  ";
    public static final String MESSAGE_TASK_LIST = "Task list:\n";
    public static final String MESSAGE_EMPTY_TASK_LIST = "Your task list is empty!";
    public static final String MESSAGE_NUMBER_OF_TASKS = "\n\nYou have %d task(s) in the list.";

    public static final String MESSAGE_NO_SUCH_TASK = "No such task!";
    public static final String MESSAGE_PROVIDE_TASK_NUMBER = "Please provide a task number!";
    public static final String MESSAGE_PROVIDE_DESCRIPTION = "Please provide a task description!";
    public static final String MESSAGE_PROVIDE_DESCRIPTION_AND_DATE = "Please provide a task description and date!";
    public static final String MESSAGE_PROVIDE_KEYWORD = "Please provide keyword(s)!";
    public static final String MESSAGE_PROVIDE_DATE = "Please provide a date!";
    public static final String MESSAGE_PROVIDE_MORE_DETAILS = "Please provide more details!";

    public static final String MESSAGE_MATCHING_TASKS = "Here are your matching task(s):\n";
    public static final String MESSAGE_NO_MATCHING_TASKS = "Hmm... there  are no matching tasks in your list!";

    public static final String MESSAGE_UNDO = "The most recent change to your task list has been undone!\n\n";
}
