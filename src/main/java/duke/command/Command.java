package duke.command;

/**
 * Encapsulates a command
 */
public class Command {
    private DukeCommandType commandType;
    private String args;

    /**
     * Constructor for a command
     *
     * @param commandType Type of command
     * @param args Other arguments to run the command
     */
    public Command(DukeCommandType commandType, String args) {
        this.commandType = commandType;
        this.args = args;
    }

    /**
     * Returns the type of command
     *
     * @return DukeCommandType of the command
     */
    public DukeCommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Returns the arguments of the command
     *
     * @return Arguments of the command
     */
    public String getArgs() {
        return this.args;
    }
}

