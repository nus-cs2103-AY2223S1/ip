package duke;

public class DukeException extends Exception {
    private static final String EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE = "Oopsieee! Command description cannot be empty";
    private static final String INTEGER_INDEX_ERROR_MESSAGE = "Oopsieee! Command \"%s\" should be followed with an integer index";
    private static final String TASK_INCORRECT_FORMAT_ERROR_MESSAGE = "Oopsieee! Task name and datetime format is incorrect.\n"
            + "Please indicate task name and datetime separated using \"%s\" for %s";
    private static final String COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE = "Oopsieee! Command \"%s\" is not in Tob Tob Dictionary";
    private static final String SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE = "Oopsieee! Command \"list\" shouldn't have any description";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Oopsieee! There are only %s tasks in Tob Tob Brain.\n"
            + "Index should be a positive number and less than %s.\n"
            + "To see the task list, input \"list\"";
    private static final String DATETIME_FORMAT_ERROR_MESSAGE = "Oopsieee! Tob Tob cannot recognize the input datetime '%s'.\n"
            + "Please make sure that it's in format 'yyyy-mm-dd'";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Oopsieee! There is no file in path %s";

    /**
     * DukeException constructor with the specified message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a <code>DukeException</code> instance with EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException emptyCommandDescriptionError() {
        return new DukeException(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
    }

    /**
     * Returns a <code>DukeException</code> instance with INTEGER_INDEX_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException integerIndexError(String command) {
        return new DukeException(String.format(INTEGER_INDEX_ERROR_MESSAGE, command));
    }

    /**
     * Returns a <code>DukeException</code> instance with TASK_INCORRECT_FORMAT_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException taskIncorrectFormatError(String separator, String taskType) {
        return new DukeException(String.format(TASK_INCORRECT_FORMAT_ERROR_MESSAGE, separator, taskType));
    }

    /**
     * Returns a <code>DukeException</code> instance with COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException commandNotRecognizedError(String command) {
        return new DukeException(String.format(COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE, command));
    }

    /**
     * Returns a <code>DukeException</code> instance with SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException shouldHaveNoDescriptionError() {
        return new DukeException(SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE);
    }

    /**
     * Returns a <code>DukeException</code> instance with INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException indexOutOfBoundsError(int size) {
        return new DukeException(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, size, size));
    }

    /**
     * Returns a <code>DukeException</code> instance with DATETIME_FORMAT_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException datetimeFormatErrorMessage(String datetimeString) {
        return new DukeException(String.format(DATETIME_FORMAT_ERROR_MESSAGE, datetimeString));
    }

    /**
     * Returns a <code>DukeException</code> instance with FILE_NOT_FOUND_ERROR_MESSAGE.
     *
     * @return <code>DukeException</code>
     */
    public static DukeException fileNotFoundError(String filePath) {
        return new DukeException(String.format(FILE_NOT_FOUND_ERROR_MESSAGE, filePath));
    }
}