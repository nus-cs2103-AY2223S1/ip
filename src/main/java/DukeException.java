public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "🙁 OOPS! " + message;
    }
}
