package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Command to exit Chacha.
 */
public class ExitCommand extends Command {
    
    /** 
     * Executes exit command.
     * 
     * @param taskList Task list.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printExit();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
    
}
