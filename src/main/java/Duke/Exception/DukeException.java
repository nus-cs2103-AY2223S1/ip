package Duke.Exception;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public static DukeException readRowFromFileException(String errorMsg) {
        return formatError("reading task from file", errorMsg);
    }

    public static DukeException createStorageFileException() {
        return formatError("creating storage file", "");
    }

    public static DukeException markTaskException(String errorMsg) {
        return formatError("marking task", errorMsg);
    }

    public static DukeException unmarkTaskException(String errorMsg) {
        return formatError("unmarking task", errorMsg);
    }

    public static DukeException deleteTaskException(String errorMsg) {
        return formatError("deleting task", errorMsg);
    }

    public static DukeException createTaskException(String errorMsg) {
        return formatError("creating task", errorMsg);
    }

    public static DukeException taskDescriptionException(String description) {
        return formatError("task description", description);
    }

    public static DukeException taskDateTimeException(String errorMsg) {
        return formatError("task's date time", errorMsg);
    }

    public static DukeException taskTokenException(int numTokens) {
        return new DukeException(String.format(
                "Number of '/' should be 0 or 1. Found %d",
                numTokens - 1));
    }

    public static DukeException dateTimeTokenException() {
        return formatError("task's date time", "Date time input not provided");
    }

    public static DukeException IndexOutOfBoundsException(int index) {
        return new DukeException(
                String.format("There is no task with index %d", index));
    }

    /**
     * Returns a DukeException that specifies location of exception to be in finding tasks.
     *
     * @param errorMsg Error message detailing error in finding task.
     * @return new DukeException with modified message.
     */
    public static DukeException findTaskException(String errorMsg) {
        return formatError("finding task", errorMsg);
    }

    /**
     * Returns a DukeException that specifies location of exception to be in the keyword.
     *
     * @param errorMsg Error message detailing error in keyword.
     * @return new DukeException with modified message.
     */
    public static DukeException keywordException(String errorMsg) {
        return formatError("keyword", errorMsg);
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
