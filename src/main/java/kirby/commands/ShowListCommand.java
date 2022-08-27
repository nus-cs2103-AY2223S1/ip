package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;

public class ShowListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        System.out.println("Here is your bag of fabulous tasks:");
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task currTask = tasks.getList().get(i);
            System.out.println(i + 1 + ": " + currTask.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
