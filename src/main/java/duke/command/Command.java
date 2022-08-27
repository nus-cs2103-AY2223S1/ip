package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Executable command that modifies the list of tasks and/or storage.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

}
