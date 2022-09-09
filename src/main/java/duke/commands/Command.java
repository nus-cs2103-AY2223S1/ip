package duke.commands;

public abstract class Command {

    /**
     * Executes the command.
     * @return True if the command executed successfully and false otherwise.
     */
    public abstract boolean execute();

}
