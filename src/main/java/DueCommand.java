import java.time.LocalDate;
import java.util.ArrayList;

public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    private LocalDate date;

    public DueCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        ArrayList<Task> dueTasks = list.getDueTasks(date);
        ui.printDueTasks(dueTasks);
    }
}