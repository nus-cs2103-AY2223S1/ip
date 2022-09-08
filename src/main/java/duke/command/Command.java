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
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @return String containing the message to user
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Flag to toggle isExit when duke.command.ExitCommand is given
     */
    public void flagExit() {
        this.isExitCommand = !this.isExitCommand;
    }

    /**
     * Getter to check if a command is an ExitCommand
     *
     * @return Boolean value whether command is an ExitCommand
     */
    public boolean isExitCommand() {
        return isExitCommand;
    }

}
