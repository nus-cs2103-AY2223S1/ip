package dukeexceptions;

public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException() {
        super(":-( OOPS! no such command exists. try again or enter help for help!");
    }
}
