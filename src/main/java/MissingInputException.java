public class MissingInputException extends DukeException{
    public String input;
    public String command;

    public MissingInputException(String input, String command) {
        super("Oops, the " + input + " of a " + command + " cannot be empty");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}