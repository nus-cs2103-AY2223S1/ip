import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskOnDateCommand extends Command {
    private LocalDate date;

    public TaskOnDateCommand(LocalDate date) {
        this.date = date;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E, d MMM yyyy"));
        ArrayList<Task> filteredTaskList = taskList.getTasksOn(date);
        ui.printWithIndent(String.format("Here are the tasks on %s:", formattedDate));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task =  filteredTaskList.get(i);
            ui.printWithIndent(i + 1 + ". " + task);
        }
    }
}
