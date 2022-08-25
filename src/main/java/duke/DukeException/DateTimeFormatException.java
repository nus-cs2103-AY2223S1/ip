package duke.DukeException;

public class DateTimeFormatException extends DukeException{
    /**
     * The error shown when user entered Date / Time in wrong format.
     * @param msg error message.
     */
    public DateTimeFormatException(String msg) {
        super(msg + "\n     ☹ OOPS!!! This date format is not supported. PLease check!");
    }
}