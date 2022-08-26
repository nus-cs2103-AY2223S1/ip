package duke.exception;

public class MatchException extends DukeException{

    public MatchException() {
        super();
    }
    @Override
    public String toString() {
        return super.toString() + "No matching tasks found!";
    }
}
