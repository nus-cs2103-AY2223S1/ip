import java.time.LocalDate;

public class ShowCommand extends Command{
    LocalDate date;

    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.nTasks(); i++) {
            Task t = tasks.getTask(i);
            LocalDate date = t.getDateMaybe();
            if (this.date.equals(date)) {
                filteredTasks.addTask(t);
            }
        }
        if (filteredTasks.nTasks() == 0) {
            ui.print("\tNo tasks found on this date.");
        } else {
            ui.print("\tHere are the tasks on this date:");
            ui.print(filteredTasks.toString());
        }
    }

}
