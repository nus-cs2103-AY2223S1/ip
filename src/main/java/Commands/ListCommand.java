package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        return t.printList();
    }

}
