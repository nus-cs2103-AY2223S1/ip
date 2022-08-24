public class WrongArgumentException extends DukeException {
    public WrongArgumentException(String invalidArg, Throwable e) {
        super(invalidArg, e);
    }
}
