package command;

import tasklist.TaskList;
import util.Storage;

/**
 * Represents a command generated from the internal save file or input from the
 * command line.
 *
 * @author Bryan Lim Jing Xiang
 */
public abstract class Command {
    private boolean isTerminated = false;

    private String outputMessage = "";

    /**
     * Executes a specific command
     *
     * @param list    Duke list that stores all current tasks
     * @param storage Storage that handles all storage related functionalities
     */
    public abstract void execute(TaskList list, Storage storage);

    public String getOutputMessage() {
        return this.outputMessage;
    }

    protected void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }
}
