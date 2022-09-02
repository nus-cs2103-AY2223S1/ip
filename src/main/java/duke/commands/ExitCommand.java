package duke.commands;

import duke.core.Main;

/**
 * A command that exits a UI.
 */
public class ExitCommand extends Command {

    /**
     * The exit message used when the user issues the exit command.
     */
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * The UI that will be exited when this command is executed.
     */
    protected Main applicationToExit;

    /**
     * Constructor for an exit command.
     *
     * @param invoker  The string used to invoke the execution of this command.
     * @param applicationToExit The UI that will be exited when this command is executed.
     */
    public ExitCommand(String invoker, Main applicationToExit) {
        super(invoker);
        this.applicationToExit = applicationToExit;
    }

    /**
     * Exits the given UI.
     *
     * @param parameters Command arugments, unused.
     * @return The exit message.ß
     */
    @Override
    public String execute(String parameters) {
        applicationToExit.exit();
        return EXIT_MESSAGE;
    }
}
