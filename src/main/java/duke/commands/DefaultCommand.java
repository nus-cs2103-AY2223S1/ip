package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DefaultCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Try typing something else!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
