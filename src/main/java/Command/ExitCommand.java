package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        this.isExit = true;
        return "bye bye! Have a good day!";
    }
}
