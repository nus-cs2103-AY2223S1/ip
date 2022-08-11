public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("%s %s", "☹ OOPS!!!", super.toString());
    }
}
