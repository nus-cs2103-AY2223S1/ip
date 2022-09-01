package dukepro.exceptions;

/**
 * Class for BadTaskOperationException.
 */
public class BadTaskOperationException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;

    /**
     * Creates a BadTaskOperationException.
     *
     * @param errorMessage the errormMessage.
     * @param taskType type of task.
     * @return A BadTaskOperationException.
     */
    public BadTaskOperationException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    /**
     * Returns String version of this.
     *
     * @return A String in specific format.
     */
    @Override
    public String toString() {
        String ret = line + "\n Please provide input that is within the length of the list.\n" + line;
        return ret;
    }
}
