package poolsheen;

/**
 * Represents a Runtime Exception which will be thrown an incomplete command is entered.
 */
public class IncompleteCommandException extends RuntimeException {
    /** The type of command. */
    String commandType;

    /** What the user should do to prevent this exception from being thrown again. */
    String whatToDo;

    /**
     * A public constructor to initialise an IncompleteCommandException object.
     *
     * @param input The input given which caused the exception to be thrown.
     * @param commandType The type of the command.
     * @param whatToDo The steps that should be taken to prevent this error from occurring.
     */
    public IncompleteCommandException(String input, String commandType, String whatToDo) {
        super(input);
        this.commandType = commandType;
        this.whatToDo = whatToDo;
    }

    @Override
    public String toString() {
        return "Poolsheen thinks you did not fill up the " + this.commandType
                + " command properly\n" + Ui.BEGIN_SPACE + this.whatToDo;
    }
}