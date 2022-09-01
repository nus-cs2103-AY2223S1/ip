package amanda.command;

import amanda.manager.*;
import amanda.ui.*;

public class ListCommand extends Command {

    public ListCommand() {

        super(null, 0);
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, StoreManager store) {
        ui.showListCommand();
        for (int i = 0; i < tasks.getList().size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getList().get(i));
        }
    }
}
