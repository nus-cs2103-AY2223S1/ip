package duke.exception;
public class MissingInputException extends DukeException {

    public MissingInputException(String input, String command) {
        super("Oops, the " + input + " of a " + command + " cannot be empty");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
