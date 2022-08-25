package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddEventsCommand extends Command {
    String taskName;
    LocalDate localDate;

    public AddEventsCommand(String taskName, String date) throws DateTimeParseException {
            this.taskName = taskName;
            this.localDate = LocalDate.parse(date);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task event = new Events(taskName, localDate);
        taskList.addTasks(event);
        storage.saveTasks(taskList);
        ui.repeater(event + " added!");
    }
}
