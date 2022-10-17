package rick.commands;

import rick.Storage;
import rick.Ui;
import rick.tasks.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     * 
     * @param instruction The instruction to be executed.
     */
    public ListCommand(String instruction) {
        super(instruction);
    }

    /**
     * Executes the command.
     * 
     * @param tasks   The task list.
     * 
     * @param ui      The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showArray(tasks);
    }

    /**
     * Returns true if the two ListCommand objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            if (obj instanceof ListCommand) {
                ListCommand other = (ListCommand) obj;
                return this.instruction.equals(other.instruction);
            } else {
                return false;
            }
        }
    }
}
