import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        ui.printLine("Here are the tasks in your list:");
        for (int i = 1; tasks.isValidTaskNumber(i); i++) {
            ui.printLine(i + ". " + tasks.getTaskToString(i));
        }
    }
}
