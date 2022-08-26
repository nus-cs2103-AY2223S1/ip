package duke.command;

import duke.exception.DukeCommandAlreadyExecutedException;
import duke.exception.DukeIoException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * @param
 * @param
 * @param
 * @param
 * @param
 * @return
 * @throws
 */
public abstract class Command {

    private static final String COMMAND_ALREADY_EXECUTED_ERROR_MESSAGE = "Oops! This command has been executed";
    private boolean isExecuted;
    private final CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
        isExecuted = false;
    }

    public boolean isExit() {
        return commandType == CommandType.EXIT;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeCommandAlreadyExecutedException {
        if (isExecuted) {
            throw new DukeCommandAlreadyExecutedException(COMMAND_ALREADY_EXECUTED_ERROR_MESSAGE);
        }
        isExecuted = true;
        executeConcretely(ui, taskList, storage);
    }

    protected void saveFile(Ui ui, TaskList taskList, Storage storage) {
        try {
            storage.saveFile(taskList.getFileStream());
        } catch (DukeIoException exception) {
            ui.printOutput(exception.getMessage());
        }
    }

    protected abstract void executeConcretely(Ui ui, TaskList taskList, Storage storage);
}
