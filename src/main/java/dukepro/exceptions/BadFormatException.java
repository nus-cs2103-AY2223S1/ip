package dukepro.exceptions;

/**
 * Class for BadFormatException.
 */
public class BadFormatException extends DukeException {
    private static final String LINE = "_______________________________________";
    private String taskType;
    private String format;
    private String separator;

    /**
     * Creates a BadFormatException.
     *
     * @param message the errormMessage.
     * @param taskType type of task.
     * @param format the correct format.
     * @return A BadFormatException.
     */
    public BadFormatException(String message, String taskType, String format, String separator) {
        super(message);
        this.taskType = taskType;
        this.format = format;
        this.separator = separator;
    }

    /**
     * Returns String version of this.
     *
     * @return A String in specific format.
     */
    @Override
    public String toString() {
        String ret = LINE + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                + " <YOUR TASK HERE> " + separator + this.format + "\n" + LINE;
        return ret;
    }
}
