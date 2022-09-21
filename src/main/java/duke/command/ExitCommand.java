package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class ExitCommand extends Command{

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        this.isExit = true;
        return "bye bye! Have a good day!";
    }
}
