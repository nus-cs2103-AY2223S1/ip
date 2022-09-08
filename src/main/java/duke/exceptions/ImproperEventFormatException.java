package duke.exceptions;

public class ImproperEventFormatException extends DukeException {
    public ImproperEventFormatException() {
        super("IMPROPER EVENT FORMAT. KINDLY FOLLOW:\n"
                + "event task /at YYYY-MM-DD hh:mm\n"
                + "i.e event cry"
                + " /at 2000-10-10 23:00");
    }
}
