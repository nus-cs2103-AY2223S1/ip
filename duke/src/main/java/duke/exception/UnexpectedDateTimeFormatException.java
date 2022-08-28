package duke.exception;

public class UnexpectedDateTimeFormatException extends DukeException{
    public UnexpectedDateTimeFormatException() {
        super("Wrong date and time format! Please give in the format DD/MM/YYYY HHmm");
    }
}
