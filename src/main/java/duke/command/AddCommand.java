package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class AddCommand extends Command {

    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
    }
}
