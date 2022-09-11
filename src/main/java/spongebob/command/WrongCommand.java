package spongebob.command;

import spongebob.Storage;
import spongebob.TaskList;

/**
 * Represents a wrong command which returns the error message.
 */
public class WrongCommand implements ICommand {
    private String errMsg;

    public WrongCommand(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * Returns the error message.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return this.errMsg;
    }

    /**
     * Returns if command is an ExitCommand.
     *
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if two WrongCommands are equal.
     *
     * @param obj Other command.
     * @return True if two WrongCommands are equal. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WrongCommand) {
            WrongCommand cmd = (WrongCommand) obj;
            return this.errMsg.equals(cmd.errMsg);
        } else {
            return false;
        }
    }
}
