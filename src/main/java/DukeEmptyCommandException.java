public class DukeEmptyCommandException extends DukeException {
    public DukeEmptyCommandException(String taskType) {
        super(String.format("☹ OOPS!!! The description of %s cannot be empty.", taskType));
    }

    public DukeEmptyCommandException(String taskType, String separator) {
        super(String.format("☹ OOPS!!! The description of %s requires %s value.", taskType, separator));
    }
}
