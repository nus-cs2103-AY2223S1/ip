public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable error) {
        super(message, error);
    }

}
