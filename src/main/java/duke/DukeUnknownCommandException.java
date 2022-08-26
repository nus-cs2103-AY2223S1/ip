package duke;

public class DukeUnknownCommandException extends DukeException{
    public DukeUnknownCommandException() {
        super("I don't know what this command means!");
    }
}
