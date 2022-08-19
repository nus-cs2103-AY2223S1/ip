public class DukeException extends Exception {
    public DukeException(String message) {
        super("☹ BEEP BOOP BUZZ ERROR!!! " + message);
    }
}