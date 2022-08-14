public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMsg) {
        super("☹ OOPS!!! I'm sorry, " + errorMsg + " :-(");
    }
}
