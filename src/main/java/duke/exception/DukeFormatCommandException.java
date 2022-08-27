package duke.exception;

public class DukeFormatCommandException extends DukeException {
    public DukeFormatCommandException(String taskType) {
        super(String.format("☹ OOPS!!! The description of %s cannot be empty.", taskType));
    }

    public DukeFormatCommandException(String taskType, String separator) {
        super(String.format("☹ OOPS!!! The description of %s requires %s value in the format " +
                "\"dd/MM/yyyy\" or \"dd/MM/yyyy HH:mm\".", taskType, separator));
    }
}
