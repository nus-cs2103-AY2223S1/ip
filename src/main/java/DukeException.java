public class DukeException extends Exception {
    private String message;
    DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
