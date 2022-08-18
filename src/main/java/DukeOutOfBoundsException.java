public class DukeOutOfBoundsException extends DukeException {
    public DukeOutOfBoundsException(int start, int end) {
        super("Your input is out of bounds. Please try a number between " + start + " and " + end + ".");
    }
}
