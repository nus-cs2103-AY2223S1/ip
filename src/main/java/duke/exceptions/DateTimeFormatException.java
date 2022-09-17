package duke.exceptions;

public class DateTimeFormatException extends Exception {
    public DateTimeFormatException() {
        super("Write all dates in the format of yyyy-MM-dd and times in the format of HH:mm");
    }
}
