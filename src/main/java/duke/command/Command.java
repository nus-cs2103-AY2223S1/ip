package duke.command;

import duke.tasklist.TaskList;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
abstract public class Command {
    private boolean isExit = false;
    private String fullCommand;

    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;
    public void setIsExitTrue(){
        this.isExit = true;
    }
    public boolean getIsExit(){
        return this.isExit;
    }
}
