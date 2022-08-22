package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String desc;
    private final LocalDate by;

    public AddDeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(this.desc, this.by);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks);
        storage.write(tasks);
    }
}
