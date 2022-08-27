package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ModifyCommand extends Command {
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

    public static CommandType getCommandType() {
        return commandType;
    }


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
        }

        storage.saveToFile(tasks);
    }
}
