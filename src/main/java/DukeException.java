public class DukeException extends Exception {
    private String message;
    private static String sep = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return sep + "\n" + this.message + "\n" + sep;
    }
}