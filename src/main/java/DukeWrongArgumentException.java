public class DukeWrongArgumentException extends Exception{
    public DukeWrongArgumentException(String errMsg, Throwable e) {
        super(errMsg, e);
    }
}
