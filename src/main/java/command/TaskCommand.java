package command;

import henry.Task;

/**
 * An interface for commands that add Tasks to the task list.
 */
public interface TaskCommand {

    Task getTask();
}
