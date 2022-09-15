package Duke.Exceptions;

/**
 * Class that denotes the Exception for invalid input as Command.
 */
public class InvalidCommandException extends DukeException{
    @Override
    public String toString() {
        return "Sorry, I don't understand your words\n : (";
    }
}
