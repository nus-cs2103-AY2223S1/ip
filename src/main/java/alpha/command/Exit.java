package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public class Exit extends Command {

    /**
     * {@inheritDoc}
     *
     * Executes nothing.
     */
    @Override
    public void execute(TaskList tasks, Ui uI, FileOperations fileOperations) {
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of the Exit class.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Exit) {
            return true;
        }
        return false;
    }
}
