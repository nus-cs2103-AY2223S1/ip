import java.time.LocalDate;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.show("No tasks. Nephew must be a failure.");
            return;
        }

        ui.show("Nephew got a lot of things to do:");

        for (int taskNum = 1; taskNum < tasks.getLength() + 1; ++taskNum) {
            Task task = tasks.get(taskNum);
            ui.show(String.valueOf(taskNum) + ". " + task.toString());
        }
    }
}
