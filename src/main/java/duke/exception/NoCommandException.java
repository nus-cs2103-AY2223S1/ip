package duke.exception;

public class NoCommandException extends DukeException{
    public NoCommandException(String command) {
        super("We expect no other commands after " + command + " command!");
    }
}
