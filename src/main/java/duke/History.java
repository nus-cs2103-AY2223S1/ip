package duke;

import duke.task.Task;

public class History {
    private String lastCommand;
    private Task lastTask;
    private String[] lastTaskDescription;
    private int lastTaskIndex;
    
    protected String getLastCommand() {
        return lastCommand;
    }

    protected void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    protected Task getLastTask() {
        return lastTask;
    }

    protected void setLastTask(Task lastTask) {
        this.lastTask = lastTask;
    }

    protected String[] getLastTaskDescription() {
        return lastTaskDescription;
    }

    protected void setLastTaskDescription(String[] lastTaskDescription) {
        this.lastTaskDescription = lastTaskDescription;
    }

    public int getLastTaskIndex() {
        return lastTaskIndex;
    }

    public void setLastTaskIndex(int lastTaskIndex) {
        this.lastTaskIndex = lastTaskIndex;
    }
}
