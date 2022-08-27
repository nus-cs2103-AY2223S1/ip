package blob.common;

/**
 * Class containing the reusable messages to print to user
 */
public class Messages {
    public static final String MESSAGE_WELCOME_1 = "Hello... me Blob...";
    public static final String MESSAGE_WELCOME_2 = "How can Blob help...?";

    public static final String MESSAGE_GOODBYE_1 = "Thanks for talking to Blob...";
    public static final String MESSAGE_GOODBYE_2 = "Blob see you soon...";

    public static final String MESSAGE_TASK_ADDED = "Blob will remember task...";
    public static final String MESSAGE_TASK_LIST_SIZE = "Blob now remembers %d tasks...";

    public static final String MESSAGE_ERROR_INVALID_DATETIME = "Blob does not understand input datetime...";
    public static final String MESSAGE_USAGE_INPUT_DATETIME = "USAGE: Dates have to be in the following formats: \n" +
            "\t<yyyy-MM-dd>, <dd-MM-yyyy>, <d MMM yyyy>, <MMM d yyyy> \n " +
            "\tOptionally include time as <H:mm> or <H:mm a> ";

    public static final String MESSAGE_ERROR_MISSING_TASK_DESCRIPTION = "Blob needs to know details of task...";
    public static final String MESSAGE_USAGE_TASK_COMMAND = "USAGE: todo/event/deadline <description> (...)";

    public static final String MESSAGE_ERROR_INVALID_DEADLINE = "Blob needs to know deadline of your task...";
    public static final String MESSAGE_USAGE_DEADLINE_COMMAND = "USAGE: deadline <description> /by <deadline>";

    public static final String MESSAGE_ERROR_INVALID_EVENT = "Blob needs to know time of your task...";
    public static final String MESSAGE_USAGE_EVENT_COMMAND = "USAGE: event <description> /at <time>";

    public static final String MESSAGE_TASK_MARKED = "Blob congratulates on task well done...";
    public static final String MESSAGE_TASK_UNMARKED = "Blob will mark as undone...";
    public static final String MESSAGE_USAGE_MARK_COMMAND = "USAGE: mark/unmark <task index>";

    public static final String MESSAGE_TASK_DELETED = "Ok... Blob forget task...";
    public static final String MESSAGE_USAGE_DELETE_COMMAND = "USAGE: delete <task index>";

    public static final String MESSAGE_ERROR_MISSING_TASK_INDEX = "Blob need to know which task index...";
    public static final String MESSAGE_ERROR_TASK_NOT_FOUND = "Blob cannot find that task..., maybe task no exist...?";
}
