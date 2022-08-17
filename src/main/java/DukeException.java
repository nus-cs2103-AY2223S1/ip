public class DukeException extends Exception {
    public DukeException() {
    }

    public static class EmptyTaskException extends DukeException {
        public EmptyTaskException() {
            super();
        }
    }
}
