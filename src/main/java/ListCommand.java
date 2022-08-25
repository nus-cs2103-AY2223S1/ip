import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (taskList.size() > 0) {
            System.out.println("\t Here are your tasks in your list:");
            taskList.printString();
        } else {
            ui.formatMessage("You do not have any tasks.");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
