package tobtob;

/**
 * Represents an error thrown by TobTob
 */
public class TobTobException extends Exception {
    private static final String EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE =
            "Oopsieee! Command description cannot be empty";
    private static final String INTEGER_INDEX_ERROR_MESSAGE =
            "Oopsieee! Command \"%s\" should be followed with an integer index";
    private static final String TASK_INCORRECT_FORMAT_ERROR_MESSAGE =
            "Oopsieee! Task name and datetime format is incorrect.\n"
            + "Please indicate task name and datetime separated using \"%s\" for %s";
    private static final String COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE =
            "Oopsieee! Command \"%s\" is not in Tob Tob Dictionary";
    private static final String SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE =
            "Oopsieee! Command \"%s\" shouldn't have any description";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE =
            "Oopsieee! There are only %s tasks in Tob Tob Brain.\n"
            + "Index should be a positive number and less than %s.\n"
            + "To see the task list, input \"list\"";
    private static final String DATETIME_FORMAT_ERROR_MESSAGE =
            "Oopsieee! Tob Tob cannot recognize the input datetime '%s'.\n"
            + "Please make sure that it's in format 'yyyy-mm-dd'";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE =
            "Oopsieee! There is no file in path %s";

    /**
     * TobTobException constructor with the specified error message
     *
     * @param message {@link String}
     */
    public TobTobException(String message) {
        super(message);
    }

    /**
     * Returns a {@link TobTobException} instance with EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE.
     *
     * @return {@link TobTobException}
     */
    public static TobTobException emptyCommandDescriptionError() {
        return new TobTobException(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
    }

    /**
     * Returns a {@link TobTobException} instance with INTEGER_INDEX_ERROR_MESSAGE.
     *
     * @param command {@link String}
     * @return {@link TobTobException}
     */
    public static TobTobException integerIndexError(String command) {
        return new TobTobException(String.format(INTEGER_INDEX_ERROR_MESSAGE, command));
    }

    /**
     * Returns a {@link TobTobException} instance with TASK_INCORRECT_FORMAT_ERROR_MESSAGE.
     *
     * @param separator {@link String}
     * @param taskType {@link String}
     * @return {@link TobTobException}
     */
    public static TobTobException taskIncorrectFormatError(String separator, String taskType) {
        return new TobTobException(String.format(TASK_INCORRECT_FORMAT_ERROR_MESSAGE, separator, taskType));
    }

    /**
     * Returns a {@link TobTobException} instance with COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE.
     *
     * @param command {@link String}
     * @return {@link TobTobException}
     */
    public static TobTobException commandNotRecognizedError(String command) {
        return new TobTobException(String.format(COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE, command));
    }

    /**
     * Returns a {@link TobTobException} instance with SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE.
     *
     * @return {@link TobTobException}
     */
    public static TobTobException shouldHaveNoDescriptionError(String command) {
        return new TobTobException(String.format(SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE, command));
    }

    /**
     * Returns a {@link TobTobException} instance with INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE.
     *
     * @param size {@code int}
     * @return {@link TobTobException}
     */
    public static TobTobException indexOutOfBoundsError(int size) {
        return new TobTobException(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, size, size));
    }

    /**
     * Returns a {@link TobTobException} instance with DATETIME_FORMAT_ERROR_MESSAGE.
     *
     * @param datetimeString {@link String}
     * @return {@link TobTobException}
     */
    public static TobTobException datetimeFormatErrorMessage(String datetimeString) {
        return new TobTobException(String.format(DATETIME_FORMAT_ERROR_MESSAGE, datetimeString));
    }

    /**
     * Returns a {@link TobTobException} instance with FILE_NOT_FOUND_ERROR_MESSAGE.
     *
     * @param filePath {@link String}
     * @return {@link TobTobException}
     */
    public static TobTobException fileNotFoundError(String filePath) {
        return new TobTobException(String.format(FILE_NOT_FOUND_ERROR_MESSAGE, filePath));
    }
}
