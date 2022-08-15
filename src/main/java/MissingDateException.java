public class MissingDateException extends DukeException {
    public MissingDateException(String taskType) {
        super("OOOOF! Date for [" + taskType + "] cannot be empty!");
    }
}