import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.list(tasks.TASKS);
    }
}
