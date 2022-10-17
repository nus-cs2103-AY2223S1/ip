package duke.functions.storage;

import duke.tasks.Task;

/**
 * Load interface with a single abstract method to load a Task into a TaskList.
 *
 * @author lauralee
 */
public interface Load {
    /**
     * Initiates the loading of a Task into a TaskList;
     *
     * @return The Task to be loaded into the TaskList.
     */
    public abstract Task loadTask();
}
