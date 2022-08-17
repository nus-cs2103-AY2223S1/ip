public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        String line = "    ____________________________________________________________";
        return line + "\n     " + message + "\n" + line;
    }
}
