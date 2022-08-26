package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "bye".
 */
public class ByeCommand extends Command {

    /**
     * Exits the program.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
