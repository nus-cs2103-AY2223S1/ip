package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Represents an exit command.
 */
public class Exit extends Command {

    /**
     * {@inheritDoc}
     *
     * Executes nothing.
     * Returns a message to indicate the completion of task.
     * @return Message to display successful completion of task.
     */
    @Override
    public String execute(TaskList tasks, Ui uI, FileOperations fileOperations) {
        return uI.returnText(">> Bye, see you soon!");
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of the Exit class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Exit) {
            return true;
        }
        return false;
    }
}
