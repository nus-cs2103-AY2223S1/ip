package util;

/**
 * Utility class containing messages used in responses.
 * They are compiled here to provide ease of access to would-be
 * translators for the application.
 */
public class TextUtils {

    // ERROR MESSAGES
    public static final String UNKNOWN_COMMAND_ERROR = "UNKNOWN COMMAND!";
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

    // OTHER MESSAGES
    public static final String TASKS_SAVED_MESSAGE = "Goodbye! Your task list has been saved!";

}
