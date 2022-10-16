package command;

/**
 *  A class which encapsulates a command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public abstract class Command {

    /**
     * Gives Duke's response after executing the required command.
     * @return Duke's response to be shown in the Dialog Box.
     */
    public abstract String execute();
}
