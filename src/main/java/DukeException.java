public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
