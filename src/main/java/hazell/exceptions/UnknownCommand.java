package hazell.exceptions;

/**
 * Exception when the user entered a command that is not recognised.
 */
public class UnknownCommand extends HazellException {
    @Override
    public String toString() {
        return String.format("%s I'm sorry, but I don't know what that means :-(", super.toString());
    }
}
