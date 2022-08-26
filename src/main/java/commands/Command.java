package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

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
