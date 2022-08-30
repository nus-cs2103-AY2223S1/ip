public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super("Invalid time bro. You need to write the time as a 24-hour time (0000 - 2359).");
    }
}
