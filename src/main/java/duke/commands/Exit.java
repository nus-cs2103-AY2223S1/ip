package duke.commands;

import duke.core.*;

public class Exit extends Command {

    /**
     * The exit message used when the user issues the exit command.
     */
    private static final String exitMessage = "Bye. Hope to see you again soon!";

    protected Ui uiToExit;
    public Exit(String invoker, Ui uiToExit) {
        super(invoker);
        this.uiToExit = uiToExit;
    }

    @Override
    public String execute(String parameters) {
        uiToExit.setIsExit(true);
        return exitMessage;
    }
}
