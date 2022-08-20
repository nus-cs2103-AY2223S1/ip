package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * A command to add a deadline task to the task list.
 * Usage: deadline <description> /by <date>
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = new Deadline(description, deadline);
        tasks.addTask(task);
        ui.showMessage("I've added this deadline.")
                .showMessage(task.toString())
                .showTaskListSize(tasks);
        storage.save(tasks);
    }
}
