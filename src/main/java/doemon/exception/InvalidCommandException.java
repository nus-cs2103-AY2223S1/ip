package doemon.exception;

public class InvalidCommandException extends DoemonException {
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s Sorry I don't recognise that command...\n\tPlease try again with a valid command",
                super.toString());
    }
}
