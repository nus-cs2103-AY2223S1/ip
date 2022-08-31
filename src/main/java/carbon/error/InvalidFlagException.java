package carbon.error;

import carbon.task.Task;

/**
 * Exception Class for invalid inputs on flags for indicating date time.
 */
public class InvalidFlagException extends CarbonException {
    private Task.Type type;
    private String flag;

    /**
     * Constructs an instance of the exception.
     *
     * @param input String message for the error.
     * @param type The type of task.
     */
    public InvalidFlagException(String input, Task.Type type) {
        super(input);
        this.type = type;
        if (type == Task.Type.DEADLINE) {
            this.flag = "/by";
        } else {
            this.flag = "/at";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format(
                "%s\n    Use '%s' to indicate the time for your %s.",
                super.toString(),
                this.flag,
                this.type
                );
    }
}
