package Duke.Exception;

/**
 * Represents the Exceptions thrown by the methods of the classes when handling data.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a DukeException that specifies error originating from loading an individual String
     * representing a Task from the data file.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException readRowFromFileException(String errorMsg) {
        return formatError("reading task from file", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from creating a storage file.
     *
     * @return DukeException.
     */
    public static DukeException createStorageFileException() {
        return formatError("creating storage file", "");
    }

    /**
     * Returns a DukeException that specifies error originating from marking a Task as complete.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException markTaskException(String errorMsg) {
        return formatError("marking task", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from marking a Task as incomplete.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException unmarkTaskException(String errorMsg) {
        return formatError("unmarking task", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from deleting a Task.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException deleteTaskException(String errorMsg) {
        return formatError("deleting task", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from creating a Task.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException createTaskException(String errorMsg) {
        return formatError("creating task", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from a parsing a Task's description.
     *
     * @param description The description that threw the error.
     * @return DukeException.
     */
    public static DukeException taskDescriptionException(String description) {
        return formatError("task description", description);
    }

    /**
     * Returns a DukeException that specifies error originating from a parsing a Task's date time.
     *
     * @param errorMsg The error message that describes the error encountered that threw the
     *                 Exception.
     * @return DukeException.
     */
    public static DukeException taskDateTimeException(String errorMsg) {
        return formatError("task's date time", errorMsg);
    }

    /**
     * Returns a DukeException that specifies error originating from identifying the number of
     * tokens to be < 1 || > 2 when parsing user input.
     *
     * @param numTokens The number of tokens found by Parser.
     * @return DukeException.
     */
    public static DukeException taskTokenException(int numTokens) {
        return new DukeException(String.format(
                "Number of '/' should be 0 or 1. Found %d",
                numTokens - 1));
    }

    /**
     * Returns a DukeException that specifies error originating from a parsing a Task's date time.
     *
     * @return DukeException.
     */
    public static DukeException dateTimeTokenException() {
        return formatError("task's date time", "Date time input not provided");
    }

    /**
     * Returns a DukeException that specifies error originating from a parsing a Task's description.
     *
     * @param index The index that was provided.
     * @return DukeException.
     */
    public static DukeException IndexOutOfBoundsException(int index) {
        return new DukeException(
                String.format("There is no task with index %d", index));
    }

    private static DukeException formatError(String prefix, String errorMsg) {
        return new DukeException(
                String.format("Error in %s - %s", prefix, errorMsg));
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
