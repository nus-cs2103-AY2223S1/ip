package Duke.DukeExceptions;

public class BadFormatException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;
    private String format;

    public BadFormatException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    public BadFormatException(String message, String taskType, String format) {
        super(message);
        this.taskType = taskType;
        this.format = format;
    }

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
        } else if (this.taskType.equals("delete") || this.taskType.equals(("done"))){
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                    + " <YOUR TASK HERE> " + " <TASK ID>\n" + line;
            return ret;
        } else {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.format + "\n" + line;
            return ret;
        }
    }
}
