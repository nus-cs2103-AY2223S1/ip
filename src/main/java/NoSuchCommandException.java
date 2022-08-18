public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException() {
        super(":-( OOPS! No such command exists. Try again!");
    }
}
