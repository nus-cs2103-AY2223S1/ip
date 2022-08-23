package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String temp = tasks.deleteTask(this.index);
        ui.printDeleteTask(tasks.get(this.index), tasks.getSize());
        storage.savetoFile(tasks.saveList());
    }
}
