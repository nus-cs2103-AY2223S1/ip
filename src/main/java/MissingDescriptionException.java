public class MissingDescriptionException extends DukeException{
    public MissingDescriptionException(String taskType) {
        super("OOOOF! Description for [" + taskType + "] cannot be empty!");
    }
}