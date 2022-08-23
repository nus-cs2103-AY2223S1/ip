package duke.commands;

import duke.core.Ui;

/**
 * A command that exits a UI.
 */
public class Exit extends Command {

    /**
     * The exit message used when the user issues the exit command.
     */
    private static final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * The UI that will be exited when this command is executed.
     */
    protected Ui uiToExit;

    /**
     * Constructor for an exit command.
     *
     * @param invoker  The string used to invoke the execution of this command.
     * @param uiToExit The UI that will be exited when this command is executed.
     */
    public Exit(String invoker, Ui uiToExit) {
        super(invoker);
        this.uiToExit = uiToExit;
    }

    /**
     * Exits the given UI.
     *
     * @param parameters Command arugments, unused.
     * @return The exit message.ß
     */
    @Override
    public String execute(String parameters) {
        uiToExit.setIsExit(true);
        return exitMessage;
    }
}
