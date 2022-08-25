package duke;

/**
 * Contains the error messages outputted by the chatbot
 */
public final class ExceptionMessages {
    static String EMPTY_TASK_DESCRIPTION = "☹ OOPS!!! The description of a %s cannot be empty.";
    static String EMPTY_TASK_TIME = "☹ OOPS!!! The date/time of a %s cannot be empty.";
    static String INVALID_FORMAT = "☹ OOPS!!! Please indicate a description and date/time separated by %s";
    static String UNSUPPORTED_ACTION = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    static String INVALID_INDEX_FORMAT = "☹ OOPS!!! Please indicate only an index for the command";
    static String LOAD_ERROR = "Error loading data from storage";
}
