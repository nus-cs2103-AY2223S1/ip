package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("I've deleted this task: \n" + tasks.get(index));
        tasks.remove(index);
    }

}
