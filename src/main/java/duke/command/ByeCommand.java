package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

public class ByeCommand extends Command{

    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
