package duke.exception;
public class EmptyDateException extends DukeException {

    public EmptyDateException() {
        super("The date field cannot be empty");
    }
    
}
