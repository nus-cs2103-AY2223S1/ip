package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;


public abstract class Command {
    private String description;
    private boolean isDone;
    private String date;

    private boolean isByeCommand = false;

    public boolean isByeCommand() {
        return isByeCommand;
    }

    public void byeCommandReceived() {
        isByeCommand = true;
    }

    public abstract void execute(TaskList taskList, Storage storage) throws DukeException;
}
