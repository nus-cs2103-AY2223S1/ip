public class DukeInvalidNumberFormatException extends DukeException {

    public DukeInvalidNumberFormatException(String command) {
        super("Invalid number format detected. Enter something like \"" + command + " 1 \"");
    }
}
