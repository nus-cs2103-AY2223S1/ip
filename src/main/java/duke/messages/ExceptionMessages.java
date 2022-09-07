package duke.messages;

/**
 * Contains the error messages outputted by the chatbot
 */
public final class ExceptionMessages {

    private static final String frowningFace = String.format("%c", 0x1F641);
    private static final String lineSep = System.lineSeparator();

    public static String EMPTY_TASK_DESCRIPTION = frowningFace
            + " OOPS!!! The description of a %s cannot be empty." + lineSep;
    public static String EMPTY_TASK_TIME = frowningFace
            + " OOPS!!! The date/time of a %s cannot be empty." + lineSep;
    public static String INVALID_FORMAT = frowningFace
            + " OOPS!!! Please indicate a description and date/time separated by %s" + lineSep;
    public static String UNSUPPORTED_ACTION = frowningFace
            + " OOPS!!! I'm sorry, but I don't know what that means :-(" + lineSep;
    public static String INVALID_INDEX_FORMAT = frowningFace
            + " OOPS!!! Please indicate only an index for the command." + lineSep;
    public static String LOAD_ERROR = "Error loading data from storage." + lineSep;
    public static String INVALID_FIND_FORMAT = frowningFace
            +" OOPS!!! Please indicate the string to scan the task list for." + lineSep;
    public static String OUT_OF_BOUNDS = frowningFace
            + "OOPS!!! Cannot %s task %d as there are only %d tasks in the list." + lineSep;
}
