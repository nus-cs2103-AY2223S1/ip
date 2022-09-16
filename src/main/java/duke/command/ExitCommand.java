package duke.command;

import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class to represent a command to exit from the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand object.
     * 
     * @param storage Storage class to be used
     * @param ui Ui class to be used
     * @param taskList TaskList to be used
     */
    public ExitCommand(Storage storage, Ui ui, TaskList taskList) {
        super(storage, ui, taskList);
    }

    /**
     * Prints a goodbye message, saves the tasks to storage and exits the program
     * 
     * @return goodbye message
     */
    @Override
    public String execute() {
        storage.saveTasks(taskList);
        return ui.bye();
    }

    /**
     * Returns true if program is supposed to exit after this command is executed.
     * 
     * @return whether program is supposed to exit after this command is executed
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
