public class IndexNotSpecifyException extends DukeException {
    public IndexNotSpecifyException(String command) {
        super("We expects an index to execute " + command + " command!");
    }
}
