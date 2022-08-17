public class InvalidFormatException extends DukeException {
    private final String sep;

    public InvalidFormatException(String sep) {
        super();
        this.sep = sep;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! Please indicate a description and date/time separated by %s", this.sep);
    }

}
