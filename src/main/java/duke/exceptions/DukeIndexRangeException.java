package duke.exceptions;

public class DukeIndexRangeException extends DukeException {
    private String command;
    private int max;

    public DukeIndexRangeException(String command, int given, int max) {
        super(String.format("Invalid parameter: %d", given + 1));
        this.max = max;
        this.command = command;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + String.format("Allowed Range for %s: 1 to %d", command, max);
    }
}
