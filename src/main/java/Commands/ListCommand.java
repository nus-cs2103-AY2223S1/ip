package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        t.printList();
    }

}
