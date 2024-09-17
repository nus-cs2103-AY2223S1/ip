package dan.exceptions;

/**
 * Dan-specific exception class
 */
public class DanException extends Exception {
    private static final String USER_INPUT_ERROR_MESSAGE = "I don't really understand what do you mean by that...";
    private static final String READ_DATAFILE_ERROR_MESSAGE = "Your data is corrupted!";
    private static final String NO_DESCRIPTION_ERROR_MESSAGE = "Please provide me a description for your %s";
    private static final String INCORRECT_FORMAT_ERROR_MESSAGE = "Please follow the following format: %s";
    private static final String EMPTY_TASKLIST_ERROR_MESSAGE = "Your list is empty!";
    private static final String TASK_INDEX_NOT_FOUND_ERROR_MESSAGE = "This task number doesn't exist!";


    public DanException(String message) {
        super("Oh no! We ran into a problem :(\n" + message);
    }

    public static DanException readDataFileError() {
        return new DanException(READ_DATAFILE_ERROR_MESSAGE);
    }
    public static DanException userInputError() {
        return new DanException(USER_INPUT_ERROR_MESSAGE);
    }
    public static DanException missingDescriptionError(String taskName) {
        return new DanException(String.format(NO_DESCRIPTION_ERROR_MESSAGE, taskName));
    }
    public static DanException incorrectFormatError(String properFormat) {
        return new DanException(String.format(INCORRECT_FORMAT_ERROR_MESSAGE, properFormat));
    }
    public static DanException emptyTaskListError() {
        return new DanException(EMPTY_TASKLIST_ERROR_MESSAGE);
    }
    public static DanException taskNotFoundError() {
        return new DanException(TASK_INDEX_NOT_FOUND_ERROR_MESSAGE);
    }


}
