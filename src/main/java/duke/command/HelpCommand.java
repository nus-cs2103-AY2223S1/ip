package duke.command;

import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class to represent a help command.
 */
public class HelpCommand extends Command{
    
    /**
     * Constructs a HelpCommand object.
     * 
     * @param storage Storage class to be used
     * @param ui Ui class to be used
     * @param taskList TaskList to be used
     */
    public HelpCommand(Storage storage, Ui ui, TaskList taskList) {
        super(storage, ui, taskList);
    }

    /**
     * Prints a help message.
     */
    @Override
    public String execute() {
        return ui.help();
    }

    /**
     * Returns false as program is not supposed to exit after this command is executed.
     * 
     * @return whether program is supposed to exit after this command is executed
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
