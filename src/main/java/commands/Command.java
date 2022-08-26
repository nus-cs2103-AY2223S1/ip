package commands;

import tasks.*;

/**
 * Command directs the program to take certain actions.
 */
public abstract class Command {
    public boolean isDone;
    public abstract void run(TaskList taskList);

    /**
     * Constructor for Command
     */
    public Command() {
        this.isDone = false;
    }
}
