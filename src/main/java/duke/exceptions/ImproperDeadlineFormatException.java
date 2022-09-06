package duke.exceptions;

public class ImproperDeadlineFormatException extends DukeException {
    public ImproperDeadlineFormatException() {
        super("IMPROPER DEADLINE FORMAT. KINDLY FOLLOW:\n"
                + "deadline task /by YYYY-MM-DD hh:mm\n"
                + "i.e deadline CS2109S PS1"
                + " /at 2000-10-10 23:00");
    }
}
