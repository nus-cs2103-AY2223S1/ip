package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * Command directs the program to take certain actions.
 */
public abstract class Command {
    public boolean isDone;
    public abstract String execute(TaskList taskList, Ui ui, Storage s);

    /**
     * Constructor for Command
     */
    public Command() {
        this.isDone = false;
    }
}
