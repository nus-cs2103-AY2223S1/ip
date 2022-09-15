package dukeexceptions;

public class DateTimeException extends DukeException {
    public DateTimeException() {
        super("please enter the date in the DD/MM/YYYY HH:MM format as so: 12/11/2022 15:30");
    }
}
