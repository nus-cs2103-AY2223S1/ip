import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OnDateCommand extends Command {
    private LocalDate localDate;

    public OnDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) {
        ArrayList<Task> list = taskList.getTaskList();
        List<Task> filteredList = list.stream().filter(task -> task.isHappeningOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        ui.line();
        System.out.println("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")));
        for (Task t : filteredList) {
            System.out.println(i + 1 + "." + t);
            i++;
        }
        ui.line();
    }
}
