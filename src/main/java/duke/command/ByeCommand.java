package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;

public class ByeCommand extends Command{

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        byeCommandReceived();
        UI.bye();
        System.exit(0);
    }

}
