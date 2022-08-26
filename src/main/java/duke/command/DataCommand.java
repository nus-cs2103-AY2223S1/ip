package duke.command;

import duke.util.ParsedData;

/**
 * DataCommand is a abstract class that stores a ParsedData.
 */
public abstract class DataCommand implements Command {

    protected ParsedData data;

    /**
     * {@inheritDoc}
     * Data commands usually always continue hence false.
     * 
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Constructor for DataCommmand.
     * 
     * @param data ParsedData from the command input
     */
    DataCommand(ParsedData data) {
        this.data = data;
    }
}
