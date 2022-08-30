public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super("Invalid time bro. You need to write the time as a 24-hour time (1600). For events, you are required "
                + "to give the start and end time (1400-1600)");
    }
}
