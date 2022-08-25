package duke.command;

import duke.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Valid commands that users can invoke to run on Duke.
 */
public abstract class Command {
    private boolean isExitCommand = false;

    /**
     * Run the given command.
     * @param taskList duke.main.TaskList containing the list of tasks.
     * @param ui duke.main.Ui dealing interaction with user.
     * @param storage duke.main.Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Flag to toggle isExit when duke.command.ExitCommand is given
     */
    public void flagExit() {
        this.isExitCommand = !this.isExitCommand;
    }

    /**
     * Getter to check if a command is an duke.command.ExitCommand
     * @return Boolean value whether command is an duke.command.ExitCommand
     */
    public boolean isExitCommand() {
        return isExitCommand;
    }

}
