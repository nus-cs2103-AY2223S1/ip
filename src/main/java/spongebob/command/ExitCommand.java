package spongebob.command;

import spongebob.Storage;
import spongebob.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements ICommand {
    /**
     * Executes end of program.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        System.exit(0);
        return "Thank you!";
    }

    /**
     * Returns if command is an ExitCommand.
     *
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns if two ExitCommands are equal.
     *
     * @param obj Other command.
     * @return True if two ExitCommands are equal. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
