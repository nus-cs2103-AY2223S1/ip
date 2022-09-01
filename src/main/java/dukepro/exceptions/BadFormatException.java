package dukepro.exceptions;

/**
 * Class for BadFormatException.
 */
public class BadFormatException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;
    private String format;

    /**
     * Creates a BadFormatException.
     *
     * @param errorMessage the errormMessage.
     * @return A BadFormatException.
     */
    public BadFormatException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    /**
     * Creates a BadFormatException.
     *
     * @param message the errormMessage.
     * @param taskType type of task.
     * @param format the correct format.
     * @return A BadFormatException.
     */
    public BadFormatException(String message, String taskType, String format) {
        super(message);
        this.taskType = taskType;
        this.format = format;
    }

    /**
     * Returns String version of this.
     *
     * @return A String in specific format.
     */
    @Override
    public String toString() {
        String separator = taskType.equals("deadline") ? "/by" : "/at";
        if (this.taskType.equals("deadline")) {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                    + " <YOUR TASK HERE> " + separator + " <DATE in yyyy-mm-dd>\n" + line;
            return ret;
        } else if (this.taskType.equals("event")) {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                    + " <YOUR TASK HERE> " + separator + " <LOCATION>\n" + line;
            return ret;
        } else if (this.taskType.equals("delete") || this.taskType.equals(("done"))) {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                    + " <YOUR TASK HERE> " + " <TASK ID>\n" + line;
            return ret;
        } else {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.format + "\n" + line;
            return ret;
        }
    }
}
