package duke.command;
import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;
import duke.task.Deadlines;
import duke.task.Task;
import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    String taskName;
    LocalDate localDate;

    public AddDeadlineCommand(String taskName, String date) {
        this.taskName = taskName;
        this.localDate = LocalDate.parse(date);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deadline = new Deadlines(taskName, localDate);
        taskList.addTasks(deadline);
        storage.saveTasks(taskList);
        ui.repeater(deadline + " added!");
    }
}
