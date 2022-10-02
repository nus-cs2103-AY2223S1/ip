package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class that lists tasks or marks task as done/undone.
 */
public class ModifyCommand extends Command {

    private final ModifyCommandType modifyCommandType;

    /**
     * Initialises ModifyCommand object with specified command type and index
     *
     * @param commandType Type of command specified by CommandType Enum
     * @param index       Index of Task from TaskList to be modified
     */
    public ModifyCommand(ModifyCommandType commandType, int index) {
        super(index);
        modifyCommandType = commandType;
    }

    /**
     * Initialises ModifyCommand object with specified command type
     *
     * @param modifyCommandType Type of command specified by CommandType Enum
     */
    public ModifyCommand(ModifyCommandType modifyCommandType) {
        this.modifyCommandType = modifyCommandType;
    }


    /**
     * Modifies/lists tasks as specified by stored command type/index.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String toReturn = "";
        switch (modifyCommandType) {
        case DONE:
            toReturn = tasks.markAsDone(index);
            break;
        case UNDONE:
            toReturn = tasks.markAsUndone(index);
            break;
        case LIST:
            toReturn = tasks.listTasks(ui);
            break;
        default:
            break;
        }

        storage.saveToFile(tasks);
        return toReturn;
    }
}
