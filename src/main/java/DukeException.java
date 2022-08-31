public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static class EmptyTaskException extends DukeException {
        public EmptyTaskException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {
        return "Oops, Duke Exception detected";
    }
}
