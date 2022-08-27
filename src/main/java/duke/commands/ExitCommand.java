package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("UwU Byebyeeee! Come back soon... Meowmeow misses you already =^._.^= ");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
