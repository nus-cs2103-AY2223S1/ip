package duke.exceptions;

public class UnrecognisedDateException extends Exception {

    public UnrecognisedDateException() {
        super("The date you entered in not in a recognised format.");
    }

}
