package dukeprogram.command;


import dukeprogram.InternalAction;

/**
 * A general purpose Command that allows the executing without additional
 * input or parsing additional input to create other commands for execution.
 */
public abstract class Command {

    private int numberOfInvokes = 0;

    /**
     * Supplies an InternalAction that defines what the main program
     * should do the moment this command state is entered. Repeated
     * calls to the same object will not trigger this function, but
     * instead trigger onStay().
     * @return an InternalAction to be performed
     */
    protected abstract InternalAction onEnter();

    /**
     * Supplies an InternalAction that defines what the main program should
     * do when the state remains the same after an action.
     * @return an InternalAction to be performed
     */
    protected abstract InternalAction onStay();

    /**
     * Parses an input string and supplies an InternalAction corresponding
     * to the input string
     * @param input the input string that was sent
     * @return an InternalAction to be performed
     */
    public abstract InternalAction onParse(String input);

    /**
     * Supplies an InternalAction that defines what the main program
     * should do the moment this command state is exited.
     * @return the next command to go to
     */
    public abstract Command onExit();

    /**
     * Invokes this command
     * @return the InternalAction to be performed
     */
    public InternalAction onInvoke() {
        numberOfInvokes++;
        if (numberOfInvokes > 1) {
            return onStay();
        }
        return onEnter();
    }
}
