public class StashyException extends Exception {
    public StashyException(String message) {
        super("☹ BEEP BOOP BUZZ ERROR!!! " + message);
    }
}