package doemon.exception;

public class InvalidCommandException extends DoemonException {
    /**
     * Returns a string representation of the exception.
     * @return a string representing the exception
     */
    @Override
    public String toString() {
        return String.format("%s Sorry I don't recognise that command...\n\t"
                + "Please try again with a valid command",
                super.toString());
    }
}
