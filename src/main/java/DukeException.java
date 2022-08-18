abstract public class DukeException extends Exception {
    public String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ":( OOPS!! " + this.message;
    }
}
