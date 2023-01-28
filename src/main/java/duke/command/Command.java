package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;

/**
 * Abstract Command class to represent an instruction to be executed.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public abstract class Command {

    private boolean isByeCommand = false;

    protected String response;

    public boolean isByeCommand() {
        return isByeCommand;
    }

    public void byeCommandReceived() {
        isByeCommand = true;
    }

    public String getResponse() { return response; }

    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;

}
