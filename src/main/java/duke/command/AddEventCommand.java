package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String desc;
    private final LocalDate at;

    public AddEventCommand(String desc, LocalDate at) {
        this.desc = desc;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(this.desc, this.at);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks);
        storage.write(tasks);
    }
}
