package duke.messages;

/**
 * Contains the error messages outputted by the chatbot
 */
public final class ExceptionMessages {

    private static final String frowningFace = String.format("%c", 0x1F641);

    public static String EMPTY_TASK_DESCRIPTION = frowningFace + " OOPS!!! The description of a %s cannot be empty.\n";
    public static String EMPTY_TASK_TIME = frowningFace + " OOPS!!! The date/time of a %s cannot be empty.\n";
    public static String INVALID_FORMAT = frowningFace
            + " OOPS!!! Please indicate a description and date/time separated by %s\n";
    public static String UNSUPPORTED_ACTION = frowningFace
            + " OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    public static String INVALID_INDEX_FORMAT = frowningFace
            + " OOPS!!! Please indicate only an index for the command\n";
    public static String LOAD_ERROR = "Error loading data from storage\n";
    public static String INVALID_FIND_FORMAT = frowningFace
            +" OOPS!!! Please indicate the string to scan the task list for\n";

}
