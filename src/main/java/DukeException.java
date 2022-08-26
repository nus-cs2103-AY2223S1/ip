public class DukeException extends IllegalArgumentException{
    protected String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.message + "\n";
    }
}
