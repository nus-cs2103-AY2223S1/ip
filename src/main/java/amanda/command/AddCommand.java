package amanda.command;
import amanda.task.*;
import amanda.manager.*;
import amanda.ui.*;
public class AddCommand extends Command {

    public AddCommand(Task task) {
        super(task, 0);
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, StoreManager store) {
        tasks.getList().add(this.task);
        store.store(tasks);
        ui.showAddCommand(tasks, task);
    }
}
