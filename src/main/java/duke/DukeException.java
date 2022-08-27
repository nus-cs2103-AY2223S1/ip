package duke;

public class DukeException extends Exception {

    protected static final String UNRECOGNISED_COMMAND = "i don't know what that means!";
    protected static final String OUT_OF_RANGE = "there's nothing there :<";
    protected static final String MISSING_INDEX = "pls enter an index!";
    protected static final String MISSING_DESCRIPTION = "description cannot be empty!!";
    protected static final String MISSING_DATE= "date cannot be empty!";
    protected static final String WRONG_FORMAT = "wrong format!";
    protected static final String WRONG_FORMAT_DATE = "wrong date format! pls re-enter using yyyy-mm-dd";

    public DukeException(String message) {
        super(message);
    }


}