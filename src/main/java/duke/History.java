package duke;

import duke.task.Task;

/**
 * A History object which keeps track of the history of commands.
 */
public class History {
    private String lastCommand;
    private Task lastTask;
    private String[] lastTaskDescription;
    private int lastTaskIndex;

    /**
     * Returns the String representation of the last command.
     *
     * @return The last command as a String
     */
    protected String getLastCommand() {
        return lastCommand;
    }

    /**
     * Stores the latest command as a String representation.
     */
    protected void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * Returns the latest Task.
     *
     * @return the latest Task
     */
    protected Task getLastTask() {
        return lastTask;
    }

    /**
     * Stores the latest Task.
     */
    protected void setLastTask(Task lastTask) {
        this.lastTask = lastTask;
    }

    /**
     * Returns the latest task's description.
     *
     * @return the latest task's description
     */
    protected String[] getLastTaskDescription() {
        return lastTaskDescription;
    }


    /**
     * Stores the latest task's description
     */
    protected void setLastTaskDescription(String[] lastTaskDescription) {
        this.lastTaskDescription = lastTaskDescription;
    }

    /**
     * Returns the latest task's index in task list.
     *
     * @return the latest task's index
     */
    public int getLastTaskIndex() {
        return lastTaskIndex;
    }

    /**
     * Stores the latest task's index
     */
    public void setLastTaskIndex(int lastTaskIndex) {
        this.lastTaskIndex = lastTaskIndex;
    }
}
