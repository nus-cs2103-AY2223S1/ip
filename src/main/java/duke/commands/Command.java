package duke.commands;

/**
 * This abstract class represents the commands input by the user.
 * When executed, the corresponding actions are performed on the list of
 * tasks and a response is generated.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @return Response message.
     */
    public abstract String execute();

    /**
     * Undoes the command.
     * @return Response message.
     */
    public abstract String undo();

}
