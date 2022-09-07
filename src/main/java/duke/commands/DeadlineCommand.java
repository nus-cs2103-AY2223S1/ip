package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private Deadline deadlineTask;

    public DeadlineCommand(String description, LocalDateTime deadlineTiming) {
        this.deadlineTask = new Deadline(description, deadlineTiming);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.addTask(this.deadlineTask);
        storage.appendTaskToFile(this.deadlineTask);
        ui.showAddTaskMessage(this.deadlineTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
