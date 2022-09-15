package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;

/** Represents the command to end the Duke program. */
public class ExitCommand extends Command {

    private static final String farewellMessage = "Bye. Hope to see you again soon! \n\nApplication is now closing.";

    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        storage.saveTaskList(taskList);
        return farewellMessage;
    }

    public static String getFarewellMessage() {
        return farewellMessage;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
