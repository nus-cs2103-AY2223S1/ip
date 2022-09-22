package command;

import exception.MeowerException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;
/**
 * Abstract class thats represents a user inputted command to the chatbot.  
 */
public abstract class Command {

    private boolean isEnd;

    Command(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException; //Referenced from Marcus Ong Wee's code

    public abstract Task getTask() throws MeowerException;
}
