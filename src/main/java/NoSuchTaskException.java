public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("There is no such task available.");
    }
}
