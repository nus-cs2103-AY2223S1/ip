package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class WrongCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "OOPS! I'm sorry, but I don't know what that means :(" ;
    }
}
