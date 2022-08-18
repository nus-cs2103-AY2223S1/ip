package duke.exceptions;

public class ImproperFormatException extends DukeException {

    public ImproperFormatException() {
        super("PLEASE FORMAT CORRECTLY\n" +
                "todo: todo (task)\n" +
                "deadline: deadline (task) /by (date)\n" +
                "event: event (task) /at (date)"
                );
    }
}
