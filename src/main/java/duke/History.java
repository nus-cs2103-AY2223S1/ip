package duke;

import duke.task.Task;

public class History {
    private String lastCommand;
    private Task lastTask;
    private String[] lastTaskDescription;
    private boolean isDeleteLastCommand;
    
    protected String getLastCommand() {
        return lastCommand;
    }

    protected void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
        this.isDeleteLastCommand = lastCommand.equals("delete");
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
    
    protected 
    
}
