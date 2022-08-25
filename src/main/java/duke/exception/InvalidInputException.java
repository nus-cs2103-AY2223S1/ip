package duke.exception;
public class InvalidInputException extends DukeException {

    public InvalidInputException(String input, String command) {
        super("Oops, " + input + " is not a valid argument for " + command);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
