package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.TaskList;

/**
 * ByeCommand says bye to user
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        makeTrueExit();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
