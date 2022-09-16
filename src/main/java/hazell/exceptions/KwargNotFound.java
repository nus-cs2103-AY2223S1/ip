package hazell.exceptions;

/**
 * Exception when user inputs a command without specifiying required keyword arguments.
 */
public class KwargNotFound extends HazellException {
    private final String command;
    private final String key;

    /**
     * Creates a new KwargNotFound exception.
     *
     * @param command The command that the user inputted
     * @param key The keyword that is missing
     */
    public KwargNotFound(String command, String key) {
        this.command = command;
        this.key = key;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The %s command requires a mandatory /%s option.", this.command, this.key);
    }
}
