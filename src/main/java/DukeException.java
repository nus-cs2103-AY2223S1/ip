public class DukeException extends Exception {
    DukeException(String errorString) {
        super(errorString);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
