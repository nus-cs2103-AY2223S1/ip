package ted.command;

import ted.Storage;
import ted.Ui;
import ted.exception.TedException;
import ted.task.TaskList;

/**
 * A abstract class that intended to be inherited by
 * user's custom comamnd.
 */
public abstract class Command {

    /**
     * Raw arguments string
     */
    protected String args;

    /**
     * Construct a command
     * @param args
     */
    public Command(String args) {
        this.args = args;
    }

    /**
     * To define what command does, it is an abstract method
     * and to be implemented.
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    public abstract void run(TaskList tasks, Ui ui, Storage storage) throws TedException;

    /**
     * To indicate program should exit after the command.
     * @return true if program will exit after command is run
     */
    public boolean isExit() {
        return false;
    }
}
