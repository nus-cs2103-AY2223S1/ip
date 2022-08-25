package duke.exception;
public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super();
    }

    @Override
    public String toString() {
        return "Date input invalid, please follow: dd/MM/yyyy HHmm";
    }
}
