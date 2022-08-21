package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the exit command to say bye to the user.
 */
public class ExitCommand extends Command {
    /**
     * Return true because this is the exit command.
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Says bye to user.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWithIndent("Bye. Hope to see you again soon!");
    }
}
