package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class that lists tasks or marks task as done/undone.
 */
public class ModifyCommand extends Command {
    /**
     * Types of commands that can be executed by this class.
     */
    public enum CommandType {
        LIST, UNDONE, DONE;
    }

    private static CommandType commandType;

    /**
     * Initialises ModifyCommand object with specified command type and index
     *
     * @param commandType Type of command specified by CommandType Enum
     * @param index       Index of Task from TaskList to be modified
     */
    public ModifyCommand(CommandType commandType, int index) {
        super(index);
        this.commandType = commandType;
    }

    /**
     * Initialises ModifyCommand object with specified command type
     *
     * @param commandType Type of command specified by CommandType Enum
     */
    public ModifyCommand(CommandType commandType) {
        this.commandType = commandType;
    }


    /**
     * Modifies/lists tasks as specified by stored command type/index.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (commandType) {
        case DONE:
            tasks.markAsDone(index, ui);
            break;
        case UNDONE:
            tasks.markAsUndone(index, ui);
            break;
        case LIST:
            tasks.listTasks(ui);
            break;
        default:
            break;
        }

        storage.saveToFile(tasks);
    }
}
