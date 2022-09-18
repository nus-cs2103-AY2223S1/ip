package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;
/**
 * Abstract class thats represents a user inputted command to the chatbot.  
 */
public abstract class Command {

    Command() {
    }

    
    /** 
     * Checks if command will cause chatbot to end.
     * @return boolean
     */
    public boolean isEnd() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException; //Referenced from Marcus Ong Wee's code

    public abstract Task getTask() throws DukeException;

}
