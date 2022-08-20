public class BadTaskOperationException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;

    public BadTaskOperationException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        String ret = line + "\n Please provide input that is within the length of the list.\n" + line;
        return ret;
    }
}