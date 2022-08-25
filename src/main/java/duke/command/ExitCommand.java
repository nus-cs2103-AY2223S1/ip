package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command{
    public ExitCommand() {
    }

    //Do nothing for exit command, simply exits the UI
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Adios Amigo! See you soon!" ;
    }
}
