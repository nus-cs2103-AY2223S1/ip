package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;

/**
 * This abstract represents the commands provided by Duke. Currently 
 * possible commands are ADD, DELETE, MAKR, UNMARK, LIST, EXIT.
 */
public abstract class Command {

    /** Returns true if this command is the exit command.
     * 
     * @return True if this is the exit command. False otherwise.
     */
    public boolean isExit() {
        return false;
    } 
    
    /**
     * Execute the action prescribed by the command.
     * 
     * @param tasks The todo list that the command is operating on.
     * @param ui The ui which the result of the execution will be displayed.
     * @param storage The storage to be updated by the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
