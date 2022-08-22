public class BadFormatException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;

    public BadFormatException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
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
        } else {
            String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                    + " <YOUR TASK HERE> " + " <ALL TASK IDs>\n" + line;
            return ret;
        }
    }
}
