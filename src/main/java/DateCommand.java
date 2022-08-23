import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    private LocalDate date;
    private ArrayList<Task> matchingTasks;

    public DateCommand(LocalDate date) {
        this.date = date;
        this.matchingTasks = new ArrayList<>();
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            if (task.getTime().equals(date)) {
                matchingTasks.add(task);
            }
        }
        ui.showDate(matchingTasks);
    }
}
